package ort.da.hipodromo.modelo.Apuesta;

public class Triple extends ModalidadApuesta {

    public Triple() {
        super("Triple");
    }

    @Override
    public double calcularCosto(double monto){
        return monto * 1.5;
    }

    @Override
    public double calcularPago(double monto, double dividendoFinal, double totalApostadoRegistro){
        if(totalApostadoRegistro >= 100000){
            return monto * dividendoFinal * 3;
        }
        return monto * dividendoFinal * 2;
    }
}