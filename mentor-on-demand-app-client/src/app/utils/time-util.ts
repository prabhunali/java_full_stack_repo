export class TimeUtil {

    static parseTime(date: Date, local: string) {
        // local = en-US (12-hour with AM/PM); en-GB (24-hour time without AM/PM)
        if(local === '') {
            // System Default
            return  date.toLocaleTimeString();
        } else {
            return date.toLocaleTimeString(local);
        }
    }

    static parseMeridian(date: Date, local: string) {
        let time = this.parseTime(date, local);
        return time.slice(-2);
    }

}
