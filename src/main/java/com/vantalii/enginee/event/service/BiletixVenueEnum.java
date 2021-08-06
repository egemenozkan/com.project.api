package com.vantalii.enginee.event.service;

public enum BiletixVenueEnum {
	ANTALYA_SAHNE(1056, "Antalya Sahne","K5"), ASPENDOS_ARENA(1046, "Aspendos Arena","AE"), JOLLY_JOKER_ANTALYA(1044, "Jolly Joker Antalya","95"),
	HOLLY_STONE_PERFORMANCE_HALL(1064, "Holly Stone Performance Hall","GN"), HARRYS_PUB(1065, "Harry\\u0027s Pub Alanya","3V"),
	KONYAALTI_BEL_NAZIM_HIKMET(1059, "Konyaaltı Belediyesi Nazım Hikmet","TT"),
	MURATPASA_KULTUR_SALONU(1058, "Muratpaşa Belediyesi Kültür Salonu","F9"), TURKAN_SORAY_KM(1043,"Türkan Şoray Kültür Merkezi","B6"),
	ANTALYA_KM(1050,"Antalya Kültür Merkezi","A1"), SPONGE_PUB(1057, "Sponge Pub","D1"), ALANYA_KM(1063, "Alanya Kültür Merkezi","83"),
	EPM_BUYUK_SAHNE(1049, "EPM Büyük Sahne","5H"), AKRA_HOTELS_ANTALYA(21, "Akra Hotels Antalya","DL"), MANAVGAT_ACIKHAVA(1060, "Manavgat Açıkhava Sahnesi","AJ"),
	ALANYA_ACIKHAVA(1055, "Alanya Açıkhava Tiyatrosu","OF"), MOA_GM(6, "Mall of Antalya Gösteri Merkezi","6C"), IBRAHIM_SOZEN_GM(1062, "İbrahim Sözen Gençlik Merkezi","25"),
	MONTGOMERIE_MAXX(259,"Montgomerie Maxx Royal Belek", "4R"), SANDLAND_LARA(833, "Sandland Lara", "BC"), MANAVGAT_KM(1061, "Manavgat Kültür Merkezi", "7Z"),
	EPM_KONGRE_MERKEZI(1049, "EPM Kongre Merkezi", "6J"), EXPO_TOWER_ANTALYA(1049, "EPM Kongre Merkezi", "WP"),
	CAM_PIRAMIT(1066,"Cam Piramit", "GQ"), ASPENDOS_ANTIK_TIYATRO(1066,"Aspendos Antik Tiyatro", "0A"); ; 
	private int id;
	private String venue;
	private String venueCode;

	private BiletixVenueEnum(int id, String venue, String venueCode) {
		this.id = id;
		this.venue = venue;
		this.venueCode = venueCode;
	}
	

	public int getId() {
		return id;
	}

	public String getVenue() {
		return venue;
	}
	
	public String getVenueCode() {
		return venueCode;
	}

	public static int getIdByVenue(String venue) {
		for (BiletixVenueEnum biletixVenueEnum : BiletixVenueEnum.values()) {
			if (biletixVenueEnum.getVenue().equals(venue.trim())) {
				return biletixVenueEnum.getId();
			}
		}
		return 0;
	}
	
	public static int getIdByVenueCode(String venueCode) {
		for (BiletixVenueEnum biletixVenueEnum : BiletixVenueEnum.values()) {
			if (biletixVenueEnum.getVenueCode().equals(venueCode)) {
				return biletixVenueEnum.getId();
			}
		}
		return 0;
	}

}
