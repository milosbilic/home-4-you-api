//package home.four.you.helper;
//
//import home.four.you.model.entity.*;
//import home.four.you.model.entity.Property.HeatType;
//import home.four.you.repository.UserRepository;
//import home.four.you.service.AdService;
//import home.four.you.service.EquipmentService;
//import home.four.you.service.LocationService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Profile;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.math.BigDecimal;
//import java.util.Currency;
//import java.util.HashSet;
//import java.util.Random;
//import java.util.Set;
//
//@Component
//@Profile("test")
//public class Bootstrap {
//
//    private static Random random = new Random();
//
//    @Autowired
//    private EquipmentService equipmentService;
//
//    @Autowired
//    private LocationService locationService;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private AdService adService;
//
////	@Autowired
////	private RealEstateService realEstateService;
//
//    //todo logger
//
///*	@PostConstruct
//	public void createAds() {
//		for (int i = 0; i < 115; i++) {
////			createDummyAd();
//			createHouseAds();
//		}
//	}*/
//
///*	@PostConstruct
//	void testImageSaving() throws IOException {
//		ClassPathResource pic = new ClassPathResource("static/images/Zgrada.jpeg");
//
//		RealEstate re = realEstateService.findOne(4L);
//		logger.debug(re);
//		if (re != null) {
//			byte[] image = new byte[(int) pic.contentLength()];
//			pic.getInputStream().read(image);
//			re.setImage(image);
//			realEstateService.save(re);
//		}
//	}*/
//
//    @Transactional
//    void createHouseAds() {
//        House h = new House();
//        h.setArea(random.nextInt(200) + 10);
//        h.setRoomsNumber(random.nextInt(5) + 0.5);
//        h.setBooked(booked());
//        h.setHeatType(getHeatType());
//        h.setEquipment(getEquipmentSet());
//        Location location = locationService.findOne(3L);
//        h.setLocation(location);
//        h.setFloorsNumber(random.nextInt(4) + 1);
//        AdType type = AdType.SALE;
//        Price price = getPriceInstance(type);
//
//        Ad ad = new Ad();
//        ad.setAdType(type);
//        ad.setTitle("Kuca na prodaju");
//        ad.setUser(getUserInstance());
//        ad.setPrice(price);
//        ad.setRealEstate(h);
//        adService.save(ad);
//    }
//
//    @Transactional
//    public void createDummyAd() {
//        Property property = getRealEstateInstance();
//        property.setArea(random.nextInt(200) + 10);
//        property.setRoomsNumber(random.nextInt(5) + 0.5);
//        property.setBooked(booked());
//        property.setHeatType(getHeatType());
//        property.setEquipment(getEquipmentSet());
//        property.setLocation(getLocationInstance());
//
//        if (property instanceof House) {
//            House house = (House) property;
//            house.setFloorsNumber(random.nextInt(4) + 1);
//            //	houseRepository.save((House) realEstate);
//        } else {
//            Apartment appartment = (Apartment) property;
//            appartment.setFloor(random.nextInt(11) + 1);
//            //	appartmentRepository.save((Appartment) realEstate);
//        }
//
//        AdType type = getAdType();
//        Price price = getPriceInstance(type);
//
//        Ad ad = new Ad();
//        ad.setAdType(type);
//        ad.setTitle("Dummy Ad");
//        ad.setUser(getUserInstance());
//        ad.setPrice(price);
//        ad.setRealEstate(property);
//        adService.save(ad);
//    }
//
//
//    private Property getRealEstateInstance() {
//        Property retVal = null;
//        switch (random.nextInt(2) + 1) {
//            case 1:
//                retVal = new House();
//                break;
//            case 2:
//                retVal = new Apartment();
//                break;
//        }
//        return retVal;
//    }
//
//    private Equipment getEquipmentInstance() {
//        Equipment retVal = null;
//        switch (random.nextInt(3) + 1) {
//            case 1:
//                retVal = equipmentService.findOne(1L);
//                break;
//            case 2:
//                retVal = equipmentService.findOne(2L);
//                break;
//            case 3:
//                retVal = equipmentService.findOne(3L);
//                break;
//            case 4:
//                retVal = equipmentService.findOne(4L);
//                break;
//            case 5:
//                retVal = equipmentService.findOne(5L);
//                break;
//            case 6:
//                retVal = equipmentService.findOne(6L);
//                break;
//        }
//        return retVal;
//    }
//
//    private Set<Equipment> getEquipmentSet() {
//        Set<Equipment> retVal = new HashSet<>();
//        int iterations = random.nextInt(6) + 1;
////		for (int i = 0; i < iterations; i++) {
//        Equipment e = getEquipmentInstance();
//        retVal.add(e);
////		}
//        return retVal;
//    }
//
//    private Location getLocationInstance() {
//        Location retVal = null;
//        switch (random.nextInt(3) + 1) {
//            case 1:
//                retVal = locationService.findOne(1L);
//                break;
//            case 2:
//                retVal = locationService.findOne(2L);
//                break;
//            case 3:
//                retVal = locationService.findOne(3L);
//                break;
//            case 4:
//                retVal = locationService.findOne(4L);
//                break;
//            case 5:
//                retVal = locationService.findOne(5L);
//                break;
//        }
//        return retVal;
//    }
//
//    /*
//     * private void populateLocations() { Location belgrade = new
//     * Location("Belgrade", "11000"); Location noviSad = new
//     * Location("Novi Sad", "21000"); Location subotica = new
//     * Location("Subotica", "24000"); Location nis = new Location("Nis",
//     * "18000"); locationService.save(belgrade);
//     * locationService.save(noviSad); locationService.save(subotica);
//     * locationService.save(nis); }
//     */
//
//    private boolean booked() {
//        boolean retVal = false;
//        switch (random.nextInt(2) + 1) {
//            case 1:
//                retVal = false;
//                break;
//            case 2:
//                retVal = true;
//                break;
//        }
//        return retVal;
//    }
//
//    private HeatType getHeatType() {
//        switch (random.nextInt(HeatType.values().length + 1)) {
//            case 1:
//                return HeatType.GAS;
//            case 3:
//                return HeatType.WOOD;
//        }
//        return null;
//    }
//
//    private Price getPriceInstance(Ad.Type type) {
//        Price retVal = new Price();
//        retVal.setCurrency(Currency.getInstance("EUR"));
//        switch (type) {
//            case SALE -> retVal.setAmount(new BigDecimal(random.nextInt(200000) + 10000));
//            case RENT -> retVal.setAmount(new BigDecimal(random.nextInt(1500) + 100));
//        }
//        return retVal;
//    }
//
//    private Ad.Type getAdType() {
//        return switch (random.nextInt(4) + 1) {
//            case 1 -> Ad.Type.SALE;
//            case 3 -> Ad.Type.RENT;
//            default -> null;
//        };
//    }
//
//    private User getUserInstance() {
//        User retVal = null;
//        switch (random.nextInt(3) + 1) {
//            case 1:
//                retVal = userRepository.getReferenceById(1L);
//                break;
//            case 2:
//                retVal = userRepository.getReferenceById(2L);
//                break;
//            case 3:
//                retVal = userRepository.getReferenceById(3L);
//                break;
//            default:
//                break;
//        }
//        return retVal;
//    }
//
//}
