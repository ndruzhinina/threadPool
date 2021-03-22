
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SpaceStation implements CommandExecutor {

    private SignalTransferMedia media;
    private List<String> knownCommands;
    private String name;

    public SpaceStation(String name, SignalTransferMedia media) {
        this.media = media;
        this.name = name;
        knownCommands = new ArrayList<>();
        knownCommands.add("gettime (alias: gt)");
        knownCommands.add("takephoto (alias: tp)");
    }

    @Override
    public String execCommand(String cmd) {
        if (cmd.equals("gt") || cmd.equals("gettime")) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            return dtf.format(now);
        }
        if (cmd.equals("tp") || cmd.equals("takephoto")) {
            return "                                                                                                                                                      \n" +
                    "                                                                                                                                                      \n" +
                    "                                                  ..,;;:::cccc:,'..                                                                                   \n" +
                    "                                             ..;cdkOOOxdxkkkkOOkkxdlc,.                                                                               \n" +
                    "                                          .'cx0KK0000OkocloxkOkxxdoodoc;'.                                                                            \n" +
                    "                                        .:dO0K0OOOOOkxdolcclxxoc::,',,,,::,'.                                                                         \n" +
                    "                                      .ck0OO00Okdoooll:;:looc::;..',,,'.':lol:.                                                                       \n" +
                    "                                     ;xO0Okkdoc:;,,,,:ccooddool,..''''''';okOd:'.                                                                     \n" +
                    "                                   .lOOxollc:ccc::cc:lddddxdc,'...'''',:c,,:coc;,.                                                                    \n" +
                    "                                  .lxdlcloodxddxdl;:xOdoddxl,,;c:;clcllx0kc;::;;;,.                                                                   \n" +
                    "                                 .lollllodxkdldkOxcckKOxOOd:;lxolllc,..;dxc;loc:;;,.                                                                  \n" +
                    "                                .cdolc:ccdkOOkddxkxdxkkkOOkoccdo:;'.....','.'cdl:;;'.                                                                 \n" +
                    "                                ;xdlc:,':c;:lxdoddxOOxxOkddoooloc'.......'''';clxd:,.                                                                 \n" +
                    "                               .oxoooc,,:lc,;dkdddd0XXKkol:'.;cdl::,......'',,.,dOo;'.                                                                \n" +
                    "                               ,dooodoll:c:';ooloook0Odc;,,'.';,.';,...........':c:;;.                                                                \n" +
                    "                               ;lcox0Ol;,'',:looooddl:,;;,,...'.......'...........'';'                                                                \n" +
                    "                               ;lc:loc:ol;:xkxdolooooc;,,;'............,,.';,...';'.'.                                                                \n" +
                    "                              .:l;,;:;;;'..''.'cxxxdkd:,,;,.....  .....':::;;,...'.''.                                                                \n" +
                    "                               ;l;;:c::lodl::,',lxO00Odlc::,'''. ........';;:,.....','                                                                \n" +
                    "                               ,ddxxl;,:ok0Oxc,',codkKXX0KXK0xoc'..,;,,,....,:'....,,.                                                                \n" +
                    "                               .oxkKx;;lolloc'..,ccclldOXNNNNNKOl,';clldc..,'cd,..','                                                                 \n" +
                    "                                ;x0Xk:;cloddl,..,:lxOdcl0NNNNXOo;';lodkkl,:oclkd,....                                                                 \n" +
                    "                                .;d0KdcloodxOko:,;d0KXXXNX0kdddl,';c:oKKkxO0OOOko;'.                                                                  \n" +
                    "                                 .;xX0dxdddolxK0dk0KKXXK0Okdooc:,;;,;cdo:,lkO0xl:;.                                              ,.                   \n" +
                    "                                  .l0K0Ood0xokXX000xx0K00Oxxxdoccllc:;'...,:cl;''.                                         ..   .'.                   \n" +
                    "                                    ;dxkxx0KOdkKK00kdOX0kkxxkxdxkxool:;codolc;,,.                                         .;oocclc;,...               \n" +
                    "                                     .coddxOOdldk0KXXXXOOKXNXKK0kddxxxkkxxddoc;.                                            .;ok0000Oxc.              \n" +
                    "                                       ,oxxkKK0KXXXXXXNNNNNNNNXNX0O000Okdolc:'.                                               .;ll;'..                \n" +
                    "                                         'ck0KXXXNXXXXNNNNNNNNNNXXKK00Odl;'..                                                 .,oo'          .        \n" +
                    "                                           .,:okKXXKKXNNNXXXXXKKK0OOkdc;.                                                     .;ll'   .cdo,.lOx'      \n" +
                    "                                               .;cdxkO0000000Okkxoc;'.                                                    .',;;:lo;. .cxOOxoOXx;.     \n" +
                    "                                                   ...',;;;;,,'..                                                 .,;,,'';llc:,;dd'  .;cx0K00Kxd;     \n" +
                    "                                                                                                                   .,ol;;:,;oooooo' .,,ckXKocoxxo.    \n" +
                    "                                                                                                                    .l: .lxxkOK0xxdoooldOOo;;,;ll,    \n" +
                    "                                                                                                              ...   :xd'.o00kxxkll0X0Oxkxllc;;,,lc.   \n" +
                    "dooooollcccc:::;,,''.............          .;cll,.                .','...                  ..;l,.            ;0Oc.  .cl;;cx0kxxdod0KKK0xl:oo;,;:l;.   \n" +
                    "OO0O0000000000000O0OOOOOOkkkkkxxxxdddoodlcldOK000kdc;,cc'..,'. .;ok0K0kOkc.             .cokOOkl'            cOxd, .cxddxllxxooocdKK0KX0ocdd;;:,..    \n" +
                    "OkkkOkkOOOOkkOOOOOOOOOOOO0OOO00OO00OO000kk0kk0000O0000O0OOOOOkolx0kk0Ok0Od:,,..'..      :00000Ol;'.          :dc;..;xxodxl;:;,,,,cdddkkl,cl'';.       \n" +
                    "OkkOOkxkOOOkkOOdxO0OOO00OOkO0OOO0OxdkkoxkkkxkkkO0000O00OO00000OdxOkO0OkkOOkkkO0Okdc'.';:xK0OO0KOkx:...       :o:,. ;dl,,;coc;:lxOkxxoox;.cl'...       \n" +
                    "OOOOOOOOOOOkkkxdokOOkdkOOOkOOOOOOkodkkxkOOOkOOkOOO00OO0OO0000000OO0OdxOkO000OO00kccdO000KK000KK0Okddxxoc:;:c:oxllc:dOdl::d00OKXXOddl:cl::;'....       \n" +
                    "OOOOOOOOOOxdkOkkkkkkkkkOOOOOOOOOOOOOOOOOOOO0000OO0OOkodOOO00OkO0000OkO00OOO000000kxk00OOO000000000000KKKKK0OkOOkOKK00K000000K0KKkdl:,,,;:'. .''       \n" +
                    "OkkkxOkodOxlodxkkkocok00000OOOOOOOOOOOOOOOOkOkkOOOkkkdxOOO00OOO0OO000000O00000O00OOO000000OOO00000OO0000000kdkOOO00000OO0OO00O00kkxclxxxkkdddol;''''..\n" +
                    "ooxolc:',oxolloxkkd:cdddkOkkddkxkkOOkkOOOOOOOkkkOOOkkOOOO0000000000000000O000O0000OO00000OO00OkO00OO00000000Ok0000000000000OOO00kxo;;d00OO0Kx:dO00000O\n" +
                    "dxxo:,;:oxxkOOkkOO00Oo;,;:;,,,ccldxOOkkkkOOOkOOO0OOkOOOOOOO0O0OOOO00OOkxkOOOO00O0000000OOO00OkkO00OOO0OOO0OOOO000OOOOO00000000000000O0000000Ok00KK0KK0\n" +
                    "kxkkxkxxkOOOOOOOOOOOOOkkxxoc,....'cxlccclxkkkkOO0000000OkOOOkO000O00OOOkO00000OO00000000OOOOOkkO0OOOOOOkkkkkOOOO00000000kO00000000O00KKKK00K000KK0KK00\n" +
                    "OOkkOOxk0OxkkkkkOOOOOOOOOOOOOkxollddlloodxkkOOOO0000000OkO000000000000OkOOO0OO00OOO0000OOOOO00OO000000000000OOO000000000O000OO0000000000KKK0KKK000KK00\n" +
                    "OOOOOOOO0xoxkOkkOOOOOOOOOOOOOOOOOOOOOOOOOkkxk00OO0000OO0000000OkkO000OOO0O00OO00OOO0000OOO0000O0000000000000OOO000000OO0000KK00000KK00000KKKKKKK0K0000";
        }
        return "Unknown command";
    }

    @Override
    public int getSignalDelay() {
        return media.getDelayMs();
    }

    @Override
    public List<String> getKnownCommandList() {
        return knownCommands;
    }

    @Override
    public String getName() {
        return name;
    }
}
