package co.com.bancolombia.microservice.resolveenigmaapi;


public class Enigma {
    private int step;
    private String enigma;

    public Enigma(){

    }

    public Enigma(int step, String enigma){
        this.step=step;
        this.enigma = enigma;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public String getEnigma() {
        return enigma;
    }

    public void setEnigma(String enigma) {
        this.enigma = enigma;
    }

    @Override
    public String toString(){

        return "Enigma{Step:"+step+", Enigma:"+ enigma +"}";
    }

}
