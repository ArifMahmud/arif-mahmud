package helper;

public class ApiPath {

    //pet
    public static final String createPet = "/api/v3/pet";
    public static final String getPetById = "/api/v3/pet/";
    public static final String getPetByStatus = "/api/v3/pet/findByStatus";
    public static final String getPetByTag = "/api/v3/pet/findByTags";
    public static final String deletePetById = "/api/v3/pet/";
    public static final String updatePetById = "/api/v3/pet";

    //user
    public static final String createUser = "/api/v3/user";
    public static final String createMultipleUsers = "/api/v3/user/createWithList";
    public static final String userLogIn = "/api/v3/user/login";
    public static final String userLogOut = "/user/logout";
    public static final String getUserByName = "/api/v3/user/";
    public static final String updateUser = "/api/v3/user/";
    public static final String deleteUser = "/api/v3/user/";

    //store
    public static final String placeOrder = "/api/v3/store/order";
    public static final String store = "/api/v3/store/order/";
    public static final String petInventory = "/api/v3/store/inventory";


}
