package com.Project.beans;

public class Film {
	
	private String id;
	private String titolo;
	private String data;
	private String ora_init;
	private String ora_fine;
	private String sala_cinema;
	private String filename;
	private String path;
	
	public void setId(String id){
		this.id=id;
	}
	public String getId() {
		return this.id;
	}
	public void setTitolo(String titolo) {
		this.titolo=titolo;
	}
	public String getTitolo() {
		return this.titolo;
	}
	public void setData(String data) {
		this.data=data;
	}
	public String getData() {
		return this.data;
	}
	public void setOra_Init(String ora_init) {
		this.ora_init=ora_init;
	}
	public String getOra_Init() {
		return this.ora_init;
	}
	public void setOra_Fine(String ora_fine) {
		this.ora_fine=ora_fine;
	}
	public String getOra_Fine() {
		return this.ora_fine;
	}
	public void setSala(String sala_cinema) {
		this.sala_cinema=sala_cinema;
	}
	public String getSala() {
		return this.sala_cinema;
	}
	public void setFileName(String filename){
		this.filename=filename;
	}
	public String getFileName() {
		return this.filename;
	}
	public void setPath(String path){
		this.path=path;
	}
	public String getPath() {
		return this.path;
	}

}
