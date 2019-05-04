package app;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_voo")
public class Voo {

	  @Id
	  @Column(nullable = true, unique = true, name = "icao24")
	  private String icao24;

	  @Column(nullable = false, unique = false, name = "numero")
	  private String numero;
	  @Column(nullable = false, unique = false, name = "pais")
	  private String pais;
	  @Column(nullable = false, unique = false, name = "solo")
	  private boolean solo;

	  public Voo() {
		  
	  }
	public Voo(final List line) {
		this.icao24 = (String)line.get(0);
	    this.numero = (String)line.get(1);
	    this.pais = (String)line.get(2);
	    this.solo = (boolean)line.get(8);
	}

	public String getIcao24() {
		return icao24;
	}

	public void setIcao24(String icao24) {
		this.icao24 = icao24;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public boolean isSolo() {
		return solo;
	}
	public void setSolo(boolean solo) {
		this.solo = solo;
	}

	@Override
	 public String toString() {
	   return String.format(
	     "{icao24: %s, number: %s, country: %s, solo: %b }",
	     this.icao24,
	     this.numero,
	     this.pais,
	     this.solo);
	}
}