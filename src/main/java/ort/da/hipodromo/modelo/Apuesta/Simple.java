package ort.da.hipodromo.modelo.Apuesta;

public class Simple extends ModalidadApuesta {

    public Simple() {
        super("Simple");
    }

    @Override
    public double calcularCosto(double monto){
        return monto;
    }

    @Override
    public double calcularPago(double monto, double dividendoFinal, double totalApostadoRegistro){
        return monto * dividendoFinal;
    }
}