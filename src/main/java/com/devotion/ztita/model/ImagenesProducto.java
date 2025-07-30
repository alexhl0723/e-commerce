package com.devotion.ztita.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


/**
 * The persistent class for the imagenes_producto database table.
 * 
 */
@Entity
@Table(name="imagenes_producto")
@NamedQuery(name="ImagenesProducto.findAll", query="SELECT i FROM ImagenesProducto i")
public class ImagenesProducto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_imagen")
	private int idImagen;

	@Column(name="es_principal")
	private byte esPrincipal;

	private int orden;

	@Column(name="url_imagen")
	private String urlImagen;

	//bi-directional many-to-one association to Producto
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="id_producto")
	private Producto producto;

	public ImagenesProducto() {
	}

	public int getIdImagen() {
		return this.idImagen;
	}

	public void setIdImagen(int idImagen) {
		this.idImagen = idImagen;
	}

	public byte getEsPrincipal() {
		return this.esPrincipal;
	}

	public void setEsPrincipal(byte esPrincipal) {
		this.esPrincipal = esPrincipal;
	}

	public int getOrden() {
		return this.orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}

	public String getUrlImagen() {
		return this.urlImagen;
	}

	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}