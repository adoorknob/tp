package ui;

import instrument.Instrument;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    public static final String DUKEBOX = """
                            _.-'\\       /'-._
                        _.-'    _\\ .-. /_    '-._
                     .-'    _.-' |/.-.\\| '-._    '-.
                   .'    .-'    _||   ||_    '-.    '.
                  /    .'    .-' ||___|| '-.    '.    \\
                 /   .'   .-' _.-'-----'-._ '-.   '.   \\
                /   /   .' .-' ~       ~   '-. '.   \\   \\
               /   /   / .' ~   *   ~     ~   '. \\   \\   \\
              /   /   /.'........    *  ~   *  ~'.\\   \\   \\
              |  /   //:::::::::: ~   _____._____ \\\\   \\  |
              |  |  |/:::::::::::  * '-----------' \\|  |  |
             .--.|__||_____________________________||__|.--.
            .'   '----. .-----------------------. .----'   '.
            '.________' |o|==|o|====:====|o|==|o| '________.'
            '.________' |o|==|o|====:====|o|==|o| '________.'
            '.________' |o|==|o|====:====|o|==|o| '________.'
              |  |  ||  ____ |:| | | | | |:| ____  ||  |  |
              |  |  || |    ||:| | | | | |:||    | ||  |  |
              |  |  || |____||:           :||____| ||  |  |
              |  |  ||  |   /|:| | | | | |:|\\   |  ||  |  |
              |  |  ||  |_.` |:| | | | | |:| `._|  ||  |  |
              |  |  || .---.-'-'-'-'-'-'-'-'-.---. ||  |  |
              |  |  || |   |\\  /\\  / \\  /\\  /|   | ||  |  |
              |  |  || |   |~\\/  \\/ ~ \\/  \\/ |   | ||  |  |
              |  |  || |   | /\\ ~/\\ ~ /\\ ~/\\ |   | ||  |  |
              |  |  || |===|/  \\/  .-.  \\/  \\|===| ||  |  |
              |  |  || |   | ~ /\\ ( * ) /\\ ~ |   | ||  |  |
              |  |  || |    \\ /  \\/'-'\\/  \\ /    | ||  |  |
             /-._|__||  \\    \\ ~ /\\ ~ /\\~  /    /  ||__|_.-\\
             |-._/__/|   \\    './  .-.  \\.'    /   |\\__\\_.-|
             | | |  ||    '._    '-| |-'    _.'    ||  | | |
             | | |  ||      '._    | |    _.'      ||  | | |
             | | |  ||  __     '-._| |_.-'         ||  | | |
             | | |  || O__O        |_|             ||  | | |
             '.|_|__||_____________________________||__|_|.'
              |  |   [_____________________________]   |  |
              |  |   |/                           \\|   |  |
              '._|__.'                             '.__|_.'
            
                   ...            .                        ....                               ..                      ...     ..                              \s      
               .x888888hx    :   @88>                  .xH888888Hx.                     < .z@8"`                   .=*8888x <"?88h.                           \s
              d88888888888hxx    %8P      .u    .    .H8888888888888:       x.    .      !@88E                    X>  '8888H> '8888          u.      uL   ..  \s
             8" ... `"*8888%`     .     .d88B :@8c   888*""\\"?""*88888X    .@88k  z88u    '888E   u         .u    '88h. `8888   8888    ...ue888b   .@88b  @88R\s
            !  "   ` .xnxx.     .@88u  ="8888f8888r 'f     d8x.   ^%88k  ~"8888 ^8888     888E u@8NL    ud8888.  '8888 '8888    "88>   888R Y888r '"Y888k/"*P \s
            X X   .H8888888%:  ''888E`   4888>'88"  '>    <88888X   '?8    8888  888R     888E`"88*"  :888'8888.  `888 '8888.xH888x.   888R I888>    Y888L    \s
            X 'hn8888888*"   >   888E    4888> '     `:..:`888888>    8>   8888  888R     888E .dN.   d888 '88%"    X" :88*~  `*8888>  888R I888>     8888    \s
            X: `*88888%`     !   888E    4888>              `"*88     X    8888  888R     888E~8888   8888.+"     ~"   !"`      "888>  888R I888>     `888N   \s
            '8h.. ``     ..x8>   888E   .d888L .+      .xHHhx.."      !    8888 ,888B .   888E '888&  8888L        .H8888h.      ?88  u8888cJ888   .u./"888&  \
             `88888888888888f    888&   ^"8888*"      X88888888hx. ..!    "8888Y 8888"    888E  9888. '8888c. .+  :"^"88888h.    '!    "*888*P"   d888" Y888*"\s
              '%8888888888*"     R888"     "Y"       !   "*888888888"      `Y"   'YP    '"888*" 4888"  "88888%    ^    "88888hx.+"       'Y"      ` "Y   Y"   \s
                 ^"****""`        ""                        ^"***"`                        ""    ""      "YP'            ^"**""                               \s
            
            """;

    private final String TEXTBORDER = "*-+=+-*-+=+-*-+=+-*-+=+-*-+=+-*-+=+-*-+=+-*-+=+-+-*-+=+-+-*-+";

    private final String COMMANDLIST = """
            help: list all commands
            list: list all instruments""";

    private Scanner scanner;
    private Parser parser;

    public Ui() {
        this.scanner = new Scanner(System.in);
        this.parser = new Parser();
    }

    public void print(String s) {
        System.out.println(s);
    }

    public void printStartMessage() {
        System.out.println("Welcome to");
        System.out.println(DUKEBOX);
        System.out.println("How can I help you?");
        System.out.println(TEXTBORDER);
    }

    public String readUserInput() {
        return scanner.nextLine();
    }

    public String getCommand(String userInput) {
        return parser.parse(userInput);
    }

    public void printCommandList() {
        System.out.println(TEXTBORDER);
        System.out.println("Here is a list of available commands:");
        System.out.println(COMMANDLIST);
        System.out.println(TEXTBORDER);
    }

    public void printInstrumentList(ArrayList<Instrument> instruments) {
        System.out.println(TEXTBORDER);
        System.out.println("Here is the list of instruments:");
        for (Instrument instrument : instruments) {
            System.out.println(instrument);
        }
        System.out.println(TEXTBORDER);
    }

    public void printGoodbye() {
        System.out.println(TEXTBORDER);
        System.out.println("bye bye");
        System.out.println(TEXTBORDER);
    }

    public void printNoMatchingCommandError() {
        System.out.println(TEXTBORDER);
        System.out.println("No such command");
        System.out.println(TEXTBORDER);
    }
}
