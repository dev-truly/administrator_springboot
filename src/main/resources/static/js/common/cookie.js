let Cookie = class {
    _name;
    _time;

    constructor(name) {
        this.name(name);

    }

    name(name) {
        this._name = name;
    }

    time(time) {
        this._time = time;
    }

    get() {
        let search = this._name + "="
        let offset, end;
        if (document.cookie.length > 0) { // 쿠키가 설정되어 있다면
            offset = document.cookie.indexOf(search)
            if (offset != -1) { // 쿠키가 존재하면
                offset += search.length
                // set index of beginning of value
                end = document.cookie.indexOf(";", offset)
                // 쿠키 값의 마지막 위치 인덱스 번호 설정
                if (end == -1)
                    end = document.cookie.length
                return unescape(document.cookie.substring(offset, end))
            }
        }
        return "";
    }

    del() {
        this.time(-1);
        this.set();
    }

    set(value = "") {
        const expdate = new Date();
        if (!this._time) {
            this.time(1000 * 3600)
        }

        if (this._time > 0) {
            expdate.setTime(expdate.getTime() + this._time)
        }
        else {
            expdate.setTime(expdate.getTime() - (0 + this._time))
        }

        document.cookie = this._name + "=" + escape (value) + "; path=/; expires=" + expdate.toGMTString();
    }
}