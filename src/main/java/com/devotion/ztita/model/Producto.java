package com.devotion.ztita.model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the productos database table.
 * 
 */
@Entity
@Table(name="productos")
@NamedQuery(name="Producto.findAll", query="SELECT p FROM Producto p")
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_producto")
	private int idProducto;

	@Lob
	private String descripcion;

	private String dimensiones;

	private byte estado;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_creacion")
	private Date fechaCreacion;

	@Column(name="id_categoria")
	private int idCategoria;

	@Column(name="img_url")
	private String imgUrl;

	private String marca;

	private String nombre;

	private BigDecimal peso;

	private BigDecimal precio;

	private int stock;

	//bi-directional many-to-one association to DetallePedido
	@OneToMany(mappedBy="producto")
	private List<DetallePedido> detallePedidos;

	//bi-directional many-to-one association to ImagenesProducto
	@OneToMany(mappedBy="producto", cascade = CascadeType.ALL,  orphanRemoval = true, fetch = FetchType.LAZY)
	private List<ImagenesProducto> imagenesProductos;

	//bi-directional many-to-one association to Resena
	@OneToMany(mappedBy="producto")
	private List<Resena> resenas;

	public Producto() {
	}

	public int getIdProducto() {
		return this.idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDimensiones() {
		return this.dimensiones;
	}

	public void setDimensiones(String dimensiones) {
		this.dimensiones = dimensiones;
	}

	public byte getEstado() {
		return this.estado;
	}

	public void setEstado(byte estado) {
		this.estado = estado;
	}

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public int getIdCategoria() {
		return this.idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getImgUrl() {
		return this.imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getMarca() {
		return this.marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigDecimal getPeso() {
		return this.peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	public BigDecimal getPrecio() {
		return this.precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public int getStock() {
		return this.stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public List<DetallePedido> getDetallePedidos() {
		return this.detallePedidos;
	}

	public void setDetallePedidos(List<DetallePedido> detallePedidos) {
		this.detallePedidos = detallePedidos;
	}

	public DetallePedido addDetallePedido(DetallePedido detallePedido) {
		getDetallePedidos().add(detallePedido);
		detallePedido.setProducto(this);

		return detallePedido;
	}

	public DetallePedido removeDetallePedido(DetallePedido detallePedido) {
		getDetallePedidos().remove(detallePedido);
		detallePedido.setProducto(null);

		return detallePedido;
	}

	public List<ImagenesProducto> getImagenesProductos() {
		return this.imagenesProductos;
	}

	public void setImagenesProductos(List<ImagenesProducto> imagenesProductos) {
		this.imagenesProductos = imagenesProductos;
	}

	public ImagenesProducto addImagenesProducto(ImagenesProducto imagenesProducto) {
		getImagenesProductos().add(imagenesProducto);
		imagenesProducto.setProducto(this);

		return imagenesProducto;
	}

	public ImagenesProducto removeImagenesProducto(ImagenesProducto imagenesProducto) {
		getImagenesProductos().remove(imagenesProducto);
		imagenesProducto.setProducto(null);

		return imagenesProducto;
	}

	public List<Resena> getResenas() {
		return this.resenas;
	}

	public void setResenas(List<Resena> resenas) {
		this.resenas = resenas;
	}

	public Resena addResena(Resena resena) {
		getResenas().add(resena);
		resena.setProducto(this);

		return resena;
	}

	public Resena removeResena(Resena resena) {
		getResenas().remove(resena);
		resena.setProducto(null);

		return resena;
	}

}