/**
 * 
 */
this.AudioPlayer = (function() {

	AudioPlayer.States = {
			Ready: 0,
			Playing: 1,
			Loading: 2,
			Error: 3
	};

	function AudioPlayer(options) {
		this.setOptions(options);
	}

	AudioPlayer.prototype.setOptions = function(options) {
		var key, value;
		if (options == null) {
			options = {};
		}
		for (key in options) {
			value = options[key];
			this[key] = value;
		}
		if (options.el) {
			return this.setEl(options.el);
		}
	};

	AudioPlayer.prototype.setEl = function(el) {
		if (this.el) {
			this._unbindEvents();
		}
		this.el = el;
		return this._bindEvents();
	};

	return AudioPlayer;
})();

AudioPlayer.prototype.updateState = function(e) {
	var state;

	if(this.isErrored()) {
		state = AudioPlayer.States.Error;
	} else if(this.isLoading()) {
		state = AudioPlayer.States.Loading;
	} else if(this.isPlaying()) {
		state = AudioPlayer.States.Playing;
	} else {
		state = AudioPlayer.States.Ready;
	}

	if (this.state !== state) {
		this.state = state;
		if(this.ui != null) {
			this.ui.AudioPlayerUpdateState(state);
		}
	}
};

AudioPlayer.prototype.isPlaying = function() {
	return this.el && !this.el.paused;
};

AudioPlayer.prototype.isPaused = function() {
	return this.el && this.el.paused;
};

AudioPlayer.prototype.isLoading = function() {
	if (!this.state && this.isEmpty()) {
		return false;
	}
	return this.el.networkState === this.el.NETWORK_LOADING && this.el.readyState < this.el.HAVE_FUTURE_DATA;
};

AudioPlayer.prototype.isErrored = function() {
	return this.el.error || this.el.networkState === this.el.NETWORK_NO_SOURCE;
};

AudioPlayer.prototype.isEmpty = function() {
	return this.el.readyState === this.el.HAVE_NOTHING;
};

AudioPlayer.prototype.duration = function() {
	return this.el.duration;
};

AudioPlayer.prototype.percentComplete = function() {
	var number;
	number = ~~((this.el.currentTime / this.el.duration) * 10000);
	return number / 10000;
};
AudioPlayer.prototype.play = function() {
	var _ref;
	if (this.isEmpty()) {
		if ((_ref = this.ui) != null) {
			_ref.AudioPlayerUpdateState(AudioPlayer.States.Loading);
		}
	}
	return this.el.play();
};

AudioPlayer.prototype.pause = function() {
	return this.el.pause();
};

AudioPlayer.prototype.load = function() {
	return this.el.load();
};
AudioPlayer.prototype.handleEvent = function(event) {
	var _ref;
	if (_ref = event.type, __indexOf.call(this.audioPlayerEvents, _ref) >= 0) {
		return this.updateState(event);
	} else if (event.type === "timeupdate") {
		return this._timeUpdate(event);
	}
};

AudioPlayer.prototype._bindEvents = function() {
	var eventName, _i, _len, _ref;
	this.audioPlayerEvents || (this.audioPlayerEvents = ["abort", "error", "play", "playing", "seeked", "pause", "ended", "canplay", "loadstart", "loadeddata", "canplaythrough", "seeking", "stalled", "waiting", "progress"]);
	_ref = this.audioPlayerEvents;
	for (_i = 0, _len = _ref.length; _i < _len; _i++) {
		eventName = _ref[_i];
		this.el.addEventListener(eventName, this);
	}
	return this.el.addEventListener("timeupdate", this);
};

AudioPlayer.prototype._unbindEvents = function() {
	var eventName, _i, _len, _ref;
	_ref = this.audioPlayerEvents;
	for (_i = 0, _len = _ref.length; _i < _len; _i++) {
		eventName = _ref[_i];
		this.el.removeEventListener(eventName, this);
	}
	return this.el.removeEventListener("timeupdate", this);
};

AudioPlayer.prototype._timeUpdate = function(e) {
	var _ref;
	if (!this.isLoading()) {
		return (_ref = this.ui) != null ? typeof _ref.AudioPlayerTimeUpdated === "function" ? _ref.AudioPlayerTimeUpdated(this.percentComplete()) : void 0 : void 0;
	}
};
AudioPlayer.prototype.destroy = function() {
	this.ui = null;
	return this._unbindEvents();
};
this.AudioPlayerUI = (function() {

	AudioPlayerUI.prototype.transitionEvents = ["transitionend", "webkitTransitionEnd", "MSTransitionEnd", "oTransitionEnd"];

	function AudioPlayerUI(options) {
		if (options == null) {
			options = {};
		}
		this.setOptions(options);
		this.audioPlayer = new AudioPlayer({
			ui: this
		});
		this._createAudioEl();
		this._createImageEl();
		if (options.el) {
			this.setEl(options.el);
		}
		this.goToSong(0);
	}

	AudioPlayerUI.prototype.setOptions = function(options) {
		var key, value, _results;
		_results = [];
		for (key in options) {
			value = options[key];
			_results.push(this[key] = value);
		}
		return _results;
	};

	AudioPlayerUI.prototype.setEl = function(el) {
		this._unbindEvents();
		this.el = el;
		this.$el = $(this.el);
		this.$el.append(this.audioEl);
		this.$imageContainer = this.$el.find(".audio-player-image");
		this.$imageContainer.append(this.image);
		this.$progressContainer = this.$el.find(".audio-player-progress");
		this.$progressBar = this.$el.find(".audio-player-progress-bar");
		this.$button = this.$el.find(".audio-player-place-pause-button");
		this.$backButton = this.$el.find(".icon-backward");
		this.$nextButton = this.$el.find(".icon-forward");
		this.$name = this.$el.find(".audio-player-song-name");
		return this._bindEvents();
	};

	return AudioPlayerUI;

})();
AudioPlayerUI.prototype.togglePlayPause = function() {
	if (this.audioPlayer.isPlaying()) {
		return this.audioPlayer.pause();
	} else {
		return this.audioPlayer.play();
	}
};

AudioPlayerUI.prototype.goToSong = function(index) {
	var wasPlaying;
	this.currentSong = index;
	wasPlaying = this.audioPlayer.isPlaying();
	this._updateSourceAttributes(index);
	this._updateImageAttributes(index);
	this.$name[0].innerHTML = this.songs[index].name;
	this.audioPlayer.setEl(this.audioEl);
	this.$progressBar.css({
		width: 0
	});
	this.audioPlayer.load();
	if (wasPlaying) {
		return this.audioPlayer.play();
	}
};

AudioPlayerUI.prototype.nextSong = function() {
	if (this.currentSong === this.songs.length - 1) {
		return this.goToSong(0);
	} else {
		return this.goToSong(this.currentSong + 1);
	}
};

AudioPlayerUI.prototype.previousSong = function() {
	if (this.currentSong === 0) {
		return this.goToSong(this.songs.length - 1);
	} else {
		return this.goToSong(this.currentSong - 1);
	}
};

AudioPlayerUI.prototype.seek = function(e) {
	var duration, offset, percent, seekTo, _ref;
	if (offset = e.offsetX || ((_ref = e.originalEvent) != null ? _ref.layerX : void 0)) {
		percent = offset / this.$progressContainer.width();
		duration = this.audioPlayer.duration();
		seekTo = duration * percent;
		return this.audioPlayer.seekTo(seekTo);
	}
};
AudioPlayerUI.prototype.AudioPlayerUpdateState = function() {
	this.$el.toggleClass("error", this.audioPlayer.isErrored());
	this.$progressContainer.toggleClass("loading", this.audioPlayer.isLoading());
	if (this.audioPlayer.isPlaying()) {
		return this.$button.removeClass("icon-play").addClass("icon-pause");
	} else {
		return this.$button.removeClass("icon-pause").addClass("icon-play");
	}
};

AudioPlayerUI.prototype.AudioPlayerTimeUpdated = function(percentComplete) {
	return this.$progressBar.css({
		width: "" + (percentComplete * 100) + "%"
	});
};
AudioPlayerUI.prototype._createImageEl = function() {
	return this.image = document.createElement("img");
};

AudioPlayerUI.prototype._createAudioEl = function() {
	return this.audioEl = document.createElement("audio");
};

AudioPlayerUI.prototype._updateSourceAttributes = function(index) {
	var source, sourceEl, _i, _len, _ref, _results;
	while (this.audioEl.firstChild) {
		this.audioEl.removeChild(this.audioEl.firstChild);
	}
	_ref = this.songs[index].srcs;
	_results = [];
	for (_i = 0, _len = _ref.length; _i < _len; _i++) {
		source = _ref[_i];
		sourceEl = document.createElement("source");
		sourceEl.setAttribute("src", source.src);
		sourceEl.setAttribute("type", source.type);
		_results.push(this.audioEl.appendChild(sourceEl));
	}
	return _results;
};

AudioPlayerUI.prototype._updateImageAttributes = function(index) {
	var callback, secondImage,
	_this = this;
	callback = function() {
		_this.image.removeAttribute("class");
		$(_this.image).off(_this.transitionEvents.join(" "));
		_this.image.setAttribute("src", _this.songs[index].image);
		return setTimeout(function() {
			if (secondImage) {
				return _this.$imageContainer[0].removeChild(secondImage);
			}
		}, 500);
	};
	if (Modernizr.csstransitions && this.$imageContainer && this.image.getAttribute("src")) {
		secondImage = document.createElement("img");
		secondImage.setAttribute("src", this.songs[index].image);
		this.image.setAttribute("class", "fading");
		this.$imageContainer.append(secondImage);
		return $(this.image).on(this.transitionEvents.join(" "), callback);
	} else {
		return callback();
	}
};
AudioPlayerUI.prototype._bindEvents = function() {
	this.$button.on("click", $.proxy(this, "togglePlayPause"));
	this.$backButton.on("click", $.proxy(this, "previousSong"));
	this.$nextButton.on("click", $.proxy(this, "nextSong"));
	return this.$progressContainer.on("mouseup", $.proxy(this, "seek"));
};

AudioPlayerUI.prototype._unbindEvents = function() {
	var _ref, _ref1, _ref2, _ref3;
	if ((_ref = this.$button) != null) {
		_ref.off("click", this.togglePlayPause);
	}
	if ((_ref1 = this.$backButton) != null) {
		_ref1.off("click", this.previousSong);
	}
	if ((_ref2 = this.$nextButton) != null) {
		_ref2.off("click", this.nextSong);
	}
	return (_ref3 = this.$progressContainer) != null ? _ref3.off("mouseup", this.seek) : void 0;
};
this.audioPlayer = new AudioPlayerUI({
	el: document.getElementById("audio-player"),
	songs: [
	        {
	        	image: "C:\Users\Felipe\Pictures\Dragon Ball Wallpaper Full HD (3).jpg",
	        	name: "Sunhawk - Stolen Glances",
	        	srcs: [
	        	       {
	        	    	   src: "C:\Users\Felipe\Documents\ProjetosRepo\WebContent\Musica\audio\SlipKnot - Before I Forget.mp3",
	        	    	   type: "audio/mp3"
	        	       }
	        	       ]
	        }, {
	        	image: "images/sunhawk-small-2@2x.jpg",
	        	name: "Sunhawk - Shotgun Love",
	        	srcs: [
	        	       {
	        	    	   src: "ShotgunLove.mp3",
	        	    	   type: "audio/mp3"
	        	       }, {
	        	    	   src: "ShotgunLove.m4a",
	        	    	   type: "audio/mp4"
	        	       }, {
	        	    	   src: "ShotgunLove.ogg",
	        	    	   type: "audio/ogg"
	        	       }
	        	       ]
	        }
	        ]
});