package dto;

import java.io.Serializable;
import java.sql.Date;

public class DtoHistorico implements Serializable {
	private int codigo;
	private Date data;
	private DtoMusica codigoMusica;
	private UsuarioDTO codigoUsuario;
	
	public DtoHistorico()
	{
		this.codigo = 0;
		this.data = null;
		this.codigoMusica = null;
		this.codigoUsuario = null;
	}

	public DtoHistorico(int codigo, Date data, DtoMusica codigoMusica,
			UsuarioDTO codigoUsuario) {
		super();
		this.codigo = codigo;
		this.data = data;
		this.codigoMusica = codigoMusica;
		this.codigoUsuario = codigoUsuario;
	}
	
	public DtoHistorico(Date data, DtoMusica codigoMusica,
			UsuarioDTO codigoUsuario) {
		super();
		this.data = data;
		this.codigoMusica = codigoMusica;
		this.codigoUsuario = codigoUsuario;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public DtoMusica getCodigoMusica() {
		return codigoMusica;
	}

	public void setCodigoMusica(DtoMusica codigoMusica) {
		this.codigoMusica = codigoMusica;
	}

	public UsuarioDTO getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(UsuarioDTO codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}
	
	
	
}
