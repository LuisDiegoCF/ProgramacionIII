package patrones.observer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PromocionComercial implements PropertyChangeListener {


    private int incentivoPromo;
    private int condicionParaPromo;

    public PromocionComercial(int cada, int incentivo) {
        condicionParaPromo = cada;
        incentivoPromo = incentivo;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        CuentaBanco ctaBanco = (CuentaBanco) evt.getSource();

        if (evt.getPropertyName().equals("SALDO")) {


            int saldoInicial = (int) evt.getOldValue();
            int saldoFinal = (int) evt.getNewValue();

            if (saldoFinal > saldoInicial) {
                int deposito = (saldoFinal - saldoInicial);
                int cuantasVecesCondicionParaPromo = (deposito / condicionParaPromo);
                int promoFinal = incentivoPromo * cuantasVecesCondicionParaPromo;

                ctaBanco.setSaldo(saldoFinal + promoFinal);
            }
            return;
        }
    }

    public int getIncentivoPromo() {
        return incentivoPromo;
    }

    public void setIncentivoPromo(int incentivoPromo) {
        this.incentivoPromo = incentivoPromo;
    }

    public int getCondicionParaPromo() {
        return condicionParaPromo;
    }

    public void setCondicionParaPromo(int condicionParaPromo) {
        this.condicionParaPromo = condicionParaPromo;
    }
}
