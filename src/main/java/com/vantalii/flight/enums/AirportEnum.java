package com.vantalii.flight.enums;

public enum AirportEnum {
    SAW(1, "İstanbul Sabiha Gökçen", "istanbul-sabiha-gokcen", "SAW", ""),
    IST(1, "İstanbul Atatürk", "istanbul-ataturk", "IST", ""),
    ESB(1, "Ankara Esenboğa", "ankara-esenboga", "ESB", ""),
    AYT(1, "Antalya", "antalya", "AYT", ""),
    ADB(1, "İzmir Adnan Menderes", "izmir-adnan-menderes", "ADB", ""),
    ADA(1, "Adana Şakirpaşa", "adana-sakirpasa", "ADA", ""),
    TZX(1, "Trabzon", "trabzon", "TZX", ""),
    DLM(1, "Muğla Dalaman", "mugla-dalaman", "DLM", ""),
    GZP(1, "Antalya Alanya Gazipaşa", "antalya-alanya-gazipasa", "GZP", ""),
    DIY(1, "Diyarbakır", "diyarbakir", "DIY", ""),
    GZT(1, "Gaziantep", "gaziantep", "GZT", ""),
    NAV(1, "Nevşehir Kapadokya", "nevsehir-kapadokya", "NAV", ""),
    ADF(1, "Adıyaman", "adiyaman", "ADF", ""),
    BGG(1, "Bingöl", "bingol", "BGG", ""),
    YEI(1, "Bursa Yenişehir", "bursa-yenisehir", "YEI", ""),
    DNZ(1, "Denizli Çardak", "denizli-cardak", "DNZ", ""),
    EZS(1, "Elazığ", "elazig", "EZS", ""),
    ERC(1, "Erzincan", "erzincan", "ERC", ""),
    ERZ(1, "Erzurum", "erzurum", "ERZ", ""),
    HTY(1, "Hatay", "hatay", "HTY", ""),
    IGD(1, "Iğdır", "igdir", "IGD", ""),
    KSY(1, "Kars", "Kars Harakani", "KSY", ""),
    ASR(1, "Kayseri", "kayseri", "ASR", ""),
    KCO(1, "Kocaeli Cengiz Topel", "kocaeli-cengiz-topel", "KCO", ""),
    KYA(1, "Konya", "konya", "KYA", ""),
    MLX(1, "Malatya", "malatya", "MLX", ""),
    SZF(1, "Samsun Çarşamba", "samsun-carsamba", "SZF", ""),
    VAS(1, "Sivas Nuri Demirağ", "sivas-nuri-demirag", "VAS", ""),
    GNY(1, "Güney Anadolu GAP Şanlıurfa", "gap-sanliurfa", "GNY", ""),
    TEQ(1, "Tekirdağ Çorlu", "tekirdag-corlu", "TEQ", ""),
    VAN(1, "Van Ferit Melen", "van-ferit-melen", "VAN", ""),
    KZR(1, "Kütahya Zafer", "kutahya-zafer", "KZR", ""),
    MQM(1, "Mardin", "mardin", "MQM", ""),
    MQJ(1, "Balıkesir Koca Seyit", "balikesir-koca-seyit", "MQJ", ""),
    KCM(1, "Kahramanmaraş", "kahramanmaras", "KCM", ""),
    AJI(1, "Ağrı", "agri", "AJI", ""),
    OGU(1, "Ordu Giresun", "ordu-giresun", "OGU", ""),
    YKO(1, "Hakkari Yüksekova Selahaddin Eyyubi", "hakkari-yuksekova-selahaddin-eyyubi", "YKO", ""),
    TJK(1, "Tokat", "tokat", "TJK", ""),
    CKZ(1, "Çanakkale", "canakkale", "CKZ", ""),
    NOTSET(1, "NOTSET", "notset", "NOTSET", "");

    

    private final int id;
    private final String name;
    private final String slug;
    private final String iata;
    private final String url;

    private AirportEnum(int id, String name, String slug, String iata, String url) {
	this.id = id;
	this.name = name;
	this.slug = slug;
	this.iata = iata;
	this.url = url;
    }

    public static AirportEnum getById(int id) {
	for (AirportEnum type : AirportEnum.values()) {
	    if (type.id == id) {
		return type;
	    }
	}
	return AirportEnum.NOTSET;
    }

    public int getId() {
	return this.id;
    }
}
