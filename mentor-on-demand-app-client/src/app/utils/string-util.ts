export class StringUtil {

    static joinStr(strings: string[], separator: string) {
        let str = '';
        for(let x=0; x < strings.length; x++) {
            str = str + strings[x];
            if(x < (strings.length - 1)) {
                str = str + separator;
            }
        }
        return str;
    }

}
