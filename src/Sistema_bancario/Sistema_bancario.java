package Sistema_bancario;

import java.util.Scanner;

public class Sistema_bancario {

    static int contadorDep = 0, contadorRet = 0, contadorTar = 0, contadorOtros;
    static float totalDep = 0, totalRet = 0;
    static cuenta cuentas;
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("1:deposito\n2:retiro\n3:tarjeta\n4:otras\n5:salir");
            byte opc = scan.nextByte();
            float dep = 0;
            switch (opc) {
                case 1:
                    dep = deposito();
                    if (dep != 0) {
                        totalDep += dep;
                    }
                    contadorDep++;
                    break;
                case 2:
                    dep = retiro();
                    if (dep != 0) {
                        totalRet += dep;
                    }
                    contadorRet++;
                    break;
                case 3:
                    tarjeta();
                    contadorTar++;
                    break;
                case 4:
                    otras();
                    contadorOtros++;
                    break;
                case 5:
                    salir();
                    System.exit(0);
                    break;
                default:
                    System.out.println("opcion invalidad");
            }
        }
    }

    static void salir() {
        while (cuentas != null) {
            System.out.println("");
            cuentas=cuentas.sig;
        }
    }

    static float deposito() {
        cuenta aux = cuentas;
        System.out.println("ingrese la cuenta a depositar");
        long c = scan.nextLong();
        while (aux != null) {
            if (aux.getCuenta() == c) {
                System.out.println("ingrese cuanto va a depositar");
                float a = scan.nextFloat();
                aux.setSaldo(a);
                return a;
            }
            aux = aux.sig;
        }
        return 0;
    }

    static float retiro() {
        cuenta aux = cuentas;
        System.out.println("ingrese la cuenta a retirar");
        long c = scan.nextLong();
        while (aux != null) {
            if (aux.getCuenta() == c) {
                System.out.println("ingrese cuanto va a retirar");
                float a = scan.nextFloat();
                if (aux.getSaldo() > a) {
                    aux.setSaldo_(a);
                    return a;
                }
            }
            aux = aux.sig;
        }
        return 0;
    }

    static void crear() {
        long ci = scan.nextLong();
        long cue = scan.nextLong();
        float sal = scan.nextFloat();
        scan.nextLine();
        String tar = scan.nextLine();
        boolean tarj;
        if (tar.trim().toLowerCase().equals("si")) {
            tarj = true;
        } else {
            tarj = false;
        }
        if (cuentas == null) {
            cuentas = new cuenta(ci, cue, tarj, sal);
        } else {
            cuentas = new cuenta(ci, cuentas, cue, tarj, sal);
        }
    }

    static void saldo() {
        cuenta aux = cuentas;
        System.out.println("ingrese la cuenta a pedir saldo");
        long c = scan.nextLong();
        while (aux != null) {
            if (aux.getCuenta() == c) {
                System.out.println("");
            }
            aux = aux.sig;
        }
    }

    static void tarjeta() {
        cuenta aux = cuentas;
        System.out.println("ingrese la cuenta a pedir tarjeta");
        long c = scan.nextLong();
        while (aux != null) {
            if (aux.getCuenta() == c) {
                if (!aux.isTarjeta()) {
                    aux.setTarjeta(true);
                    System.out.println("tarjeta obtenida");
                    return;
                }
            }
            aux = aux.sig;
        }
    }

    static void otras() {
        System.out.println("1:crear cuenta\n2:consultar saldo\n3:regresar");
        byte opc = scan.nextByte();
        switch (opc) {
            case 1:
                crear();
                break;
            case 2:
                saldo();
                break;
            case 3:
                System.out.println("se regresara al menu principal");
                break;
            default:
                System.out.println("Opcion invalida se regresara al menu principal");
        }
    }

    static class cuenta {

        private long ci;

        cuenta sig;

        public void setCi(long ci) {
            this.ci = ci;
        }

        public void setCuenta(long cuenta) {
            this.cuenta = cuenta;
        }

        public void setTarjeta(boolean tarjeta) {
            this.tarjeta = tarjeta;
        }

        public void setSaldo(float saldo) {
            this.saldo += saldo;
        }

        public void setSaldo_(float saldo) {
            this.saldo -= saldo;
        }
        private long cuenta;
        private boolean tarjeta;
        private float saldo;

        public cuenta(long ci, long cuenta, boolean tarjeta, float saldo) {
            this.ci = ci;
            this.cuenta = cuenta;
            this.tarjeta = tarjeta;
            this.saldo = saldo;
        }

        public cuenta(long ci, cuenta sig, long cuenta, boolean tarjeta, float saldo) {
            this.ci = ci;
            this.sig = sig;
            this.cuenta = cuenta;
            this.tarjeta = tarjeta;
            this.saldo = saldo;
        }

        public long getCi() {
            return ci;
        }

        public long getCuenta() {
            return cuenta;
        }

        public boolean isTarjeta() {
            return tarjeta;
        }

        public float getSaldo() {
            return saldo;
        }

    }
}
