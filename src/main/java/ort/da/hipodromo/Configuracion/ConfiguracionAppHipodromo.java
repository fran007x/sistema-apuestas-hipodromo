package ort.da.hipodromo.Configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ort.da.hipodromo.modelo.Fachada.Sistema;


@Configuration
public class ConfiguracionAppHipodromo {

    @Bean
    public Sistema sistema (){
        return Sistema.getInstance();
    }
}
