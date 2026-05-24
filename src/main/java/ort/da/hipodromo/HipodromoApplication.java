package ort.da.hipodromo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ort.da.hipodromo.DatosDePrueba.DatosPrueba;

@SpringBootApplication
public class HipodromoApplication {

	public static void main(String[] args) {
        DatosPrueba.precargar();
		SpringApplication.run(HipodromoApplication.class, args);
	}

}
