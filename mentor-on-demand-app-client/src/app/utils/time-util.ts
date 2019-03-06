export class TimeUtil {

    // Sample Outputs:
    // '' = 17:00:00; en-US = 05:00:00 PM; en-GB = 17:00:00
    static parseTime(date: Date, local: string) {
        // local = en-US (12-hour with AM/PM); en-GB (24-hour time without AM/PM)
        if(local === '') {
            // System Default
            return  date.toLocaleTimeString();
        } else {
            return date.toLocaleTimeString(local);
        }
    }

    // Sample output: AM or PM
    static parseMeridian(date: Date, local: string) {
        let time = this.parseTime(date, local);
        return time.slice(-2);
    }

    // Sample Outputs:
    //   en-US, hour12: true = 2/20/2019, 3:48:55 PM
    //   en-US, hour12: false = 2/20/2019, 15:48:55
    static parseDateTime(date: Date, local: string, hour12: boolean) {
        return date.toLocaleString(local, {hour12: hour12});
    }

    // Input: MM/dd/yyyy (03/12/2018)
    // Output: yyyy-mm-dd (2018-03-12)
    static formatDateAsSQLDate(dateStr: string, separator: string) {
        let dateArr: string[] = dateStr.split(separator);
        let mm = dateArr[0];
        let dd = dateArr[1];
        let yyyy = dateArr[2];
        return `${yyyy}-${mm}-${dd}`;
    }

}
