package instrument;

public class Piano extends Instrument{

    public Piano(String pianoName){
        this.name = pianoName;
    }

    @Override
    public String playInstrument(){
        return "Piano Sounds";
    }
}
