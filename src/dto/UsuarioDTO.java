package dto;

import java.io.Serializable;

public class UsuarioDTO implements Serializable {
	private int codigo;
	private String login;
	private String senha;
	private String email;
	private DtoFavoritos favorito;
	
	public UsuarioDTO() { 
		this.codigo = 0;
		this.login = "";
		this.senha = "";
		this.email = "";
		this.favorito = null;
	}
	
	public UsuarioDTO(int codigo, String login, String senha, String email,
			DtoFavoritos favorito)
	{
		this.codigo = codigo;
		this.login = login;
		this.senha = senha;
		this.email = email;
		this.favorito = favorito;
	}
	
	public UsuarioDTO(String login, String senha, String email, DtoFavoritos favorito)
	{
		this.login = login;
		this.senha = senha;
		this.email = email;
		this.favorito = favorito;
	}
	
	public UsuarioDTO(String login, String senha)
	{
		this.login = login;
		this.senha = senha;
	}
	
	public UsuarioDTO(int codigo, String senha, String email)
	{
		this.codigo = codigo;		
		this.senha = senha;
		this.email = email;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public DtoFavoritos getFavorito() {
		return favorito;
	}

	public void setFavorito(DtoFavoritos favorito) {
		this.favorito = favorito;
	}
	
	
}
