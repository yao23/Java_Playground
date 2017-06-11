/**
 * Created by liyao on 6/10/17.
 */
import java.util.*;

public class PalindromePairs {
    private boolean isPalindrome(String str) {
        int len = str.length();
        if (len <= 1) {
            return true;
        } else {
            int l = 0, r = len - 1;
            char[] chars = str.toCharArray();

            while (l < r) {
                if (chars[l++] != chars[r--]) {
                    return false;
                }
            }

            return true;
        }
    }

    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> result = new ArrayList<>();
        int len = words.length;
        if (len == 0) {
            return result;
        }

        Map<String, Integer> map = new HashMap<>();
        Set<List<Integer>> set = new HashSet<>();

        for (int i = 0; i < len; i++) {
            map.put(words[i], i);
        }

        for (int j = 0; j < words.length; j++) {
            String word = words[j];
            for (int i = 0; i <= word.length(); i++) {
                String left = (i == 0) ? "" : word.substring(0,i);
                String right = (i == word.length()) ? "" : word.substring(i);
                if (isPalindrome(right)) { // left part reverse append to end of right part
                    String leftReverse = new StringBuilder(left).reverse().toString();
                    if (map.containsKey(leftReverse) && j != map.get(leftReverse)) {
                        List<Integer> tuple = new ArrayList<>();
                        tuple.add(j);
                        tuple.add(map.get(leftReverse));
                        if (set.add(tuple)) {
                            result.add(tuple);
                        }
                    }
                }

                if (isPalindrome(left)) { // right part reverse add to beginning of left part
                    String rightReverse = new StringBuilder(right).reverse().toString();
                    if (map.containsKey(rightReverse) && j != map.get(rightReverse)) {
                        List<Integer> tuple = new ArrayList<>();
                        tuple.add(map.get(rightReverse));
                        tuple.add(j);
                        if (set.add(tuple)) {
                            result.add(tuple);
                        }
                    }
                }
            }
        }

        return result;
    }

    // [] => []
    // ["bat", "tab", "cat"] => [[0, 1], [1, 0] // (["battab", "tabbat"])
    // ["abcd", "dcba", "lls", "s", "sssll"] => [[0, 1], [1, 0], [3, 2], [2, 4]] // ["dcbaabcd", "abcddcba", "slls", "llssssll"]
    // ["aibdjeffhcdie","bbjjbfbgjgjgejajbdhb","eifdaed","ibihefccgjfbe","afijhbbchjgdfgi","hdcddbbgigfjaegd","jifghjjicgebfjhb","j","ggdbdefhecggfdbfdif","gbac","hajfjeccg","faccjjieeiabdfgiib","gahggcajdjfdjfeaeg","ihdiaedceh","ja","gchihbaihgigediajid","ahjbg","iehbeb","iegj","jdigidhifgg","iahbgbcheehhdicefij","gcgffafideeefjfcdai","cg","hjgbddafbfaciagc","ihdgbcdfegacdejhed","efbiehbbib","fdgfifacagd","idejefcbgbghai","ijggaiffgbeaf","jafe","accfici","dcac","jjbdbcijbfabefhhhjge","febh","a","daibiebcgjedgi","hcj","icfiihjaadeadahhg","gffhichdg","ifg","dihbjjchghhfadjfga","jifbdhhdhfafiihccjd","fafdhi","dehdijhjhfaij","dabjdfgddgecejgdcab","aie","cc","ffg","gdefacgadebhhbf","dbechjcchgibbaiag","adaibffcjfehfjejbd","aig","fejidjhebeg","hgfccicbicedihdd","deca","bjegdbdaggdhadhh","edffgbhfic","igihaheiegchaad","abbh","beehahd","fjdifjhi","i","heddcggedie","afcjebbehadgc","ibfbgjachihecgbde","dajaehbjiacb","acaeacgdjg","beebghegjdgi","iadeecfi","bhhdhdih","dfehhieehcbacgfdb","fcehjaibciebbaibifb","bijggjgag","cda","eefcbchhgbjhiefe","hbegigcf","idgbabfhdgah","aaifg","dcjaagfibjjchdghfdfc","cdebi","defhgijhjccccf","hjc","gdbjdbf","fcfjdeejihbjhhif","hdgfgbdgbgjbcc","cbjhechhiefddbhd","hbaddacfjicfja","aadg","eghfdcdihhhha","iifgjfhebfbjjjifaghj","djbedcjfgcf","bciaiejffadifahb","hhaicgge","bcjj","eaghghajef","d","c","dea","gghafi","igcfgaajccfff","hghgfbjfdhgfa","debhca","hgje","badicehbjj","gebjceah","feggechcabfcaifh","aejhjfgfab","iibeddcgehgcfbjhe","ejibgiffii","cafbgehccj","jg","g","gggbcicgg","ggbfciebjdfgidfebg","giibj","jifbacdhigaa","hchahbhbddijeebb","ddjfeggfje","jb","fbgfbgcdgjha","fhecehgigcac","iajeeefdgbihc","fgbiihfajgcf","ahfhfeeeid","jagjfabdbhedbfjeaehe","gei","hjiihjibhhjjdgadda","aiddcgjjej","hegejffecfaeeihcaf","eccibhgdhfhdg","ccgabgeaaiffjeici","hfacfjhgafic","jbhhbecbhefbjaaaad","defjbgafjij","acbcjbea","gcjegcecegccffehj","cieedbib","jghagfdbjjhhhaedahe","hchdbgffbdjjdcdhcjf","bbcafgehifgjigg","gcecbfifi","edgfcjcdcfja","gihfjhghjfgdcbc","icgifhfjaahhi","eicjghhgfhhiei","defdf","iiihfgegdaa","habcghhahah","hbecadbffjceicdig","ifhgi","bjbb","dghidbgcjdhjghg","idjhhifedbcgbedde","jgfbab","jgeiddcaadiidibfeeef","ebhhbhicgbjchdadhcc","fbdgajgib","dggjdig","bfidhifbacggeaj","iedibieihejagbbgc","jjjiajjjidjejeaig","cag","jbaaaefdjeh","eecjcbdacdid","jjafdc","jjdgfgcbdhcafaajfj","dfbhejgeiai","ebfdbehfdcga","jecgdcjejdadiijdfhcj","dbifid","edhegihbdeeefdfjhib","jejjaegagifgdiabia","chdgfifhh","faahfjdihhccja","gghbjahieaicg","ebfbdjbidbddaffhejce","eiifjhb","bhb","efhhjjdedgbaibcch","f","gcgi","bbhaddgacbi","bchgeijihgjeeiice","iaacjg","bgaffe","heehjdhbfjadj","jdecddbhgfcdfaacaieb","iejaajbejiigdeb","fhefehbfig","acfeiea","jbeacgdggicfbbceig","ddeahejdfjc","daaai","chhfgf","b","ffibejiaede","icfcde","gbafhdcf","ecjh","dgdcbhegaiegcfigi","fahfjfgigb","cchiica","egahdjjhcabadfe","gdhgfgb","fcgfa","h","hddhageijedifbg","jdfcghhbdafje","cij","jgaeifh","eh","bcejadghjfcebfgi","eddaecafhhbfejgchd","cdeagchdaighh","iaajiejbfgi","ec","ciccf","gibajfihhbhbcjhggabf","bbjdagdjjbdgh","icidef","hedfa","agdbehg","ia","beaigjbfgba","edcdhffaheigfa","jidggghifehfjfdjg","jjicdbfhhac","eihdacbbeciddbcii","abdg","behagahajcifbibfjcbb","fhieifj","cgjhedfhcgfcc","iiggcabhifich","eeicajiadbgcfabcg","bidbfhjgjaia","febbjhjhafddccdggcaa","ihafdca","ididfahgjfgfhbgejifh","hbfhjja","iedaedcga","aghi","agjdhjbhceg","cgchfidffbdjhahc","ahhcf","gaghdfjefjffc","beiiigehegbgef","iajbda","jeeghdigif","adhigcjfc","ajfjbagbejhjj","bacai","dbifhedhiba","ggbhc","hgcgdcaiiaefacagjj","ebadfbge","hhgbigajacdi","ifdbeggfcbebhb","ijefiejeaifahajbjf","chcbjhdjggcfecid","eiaiii","bhgbeb","aajgaedbbjdheiiajffh","gjgcffgb","bigecaicfbecgeh","bacdicahigagfegidbic","jdfajccfge","dbjbagigdcdijdbddad","ceg","ibebajebijihebjcfbf","cgii","jegicj","haid","agfjgc","cfdeddfhhjeafej","gb","bfdaggbiigdbbfajc","gf","gacieaigjhfdbej","ijjbgg","ai","iiebhbbjghaehdb","hidd","egfadbgjgaf","dejchfafbbihdfedgabi","edhjhfhdacgf","ebgahehbieadhaj","ea","igjecdfhfgg","jeebbhfhceaehaiebcf","hc"]
}
