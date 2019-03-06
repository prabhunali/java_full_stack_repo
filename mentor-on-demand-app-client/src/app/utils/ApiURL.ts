export class ApiURL {

    // Api Gateway (Zuul) URL
    public static readonly ZUUL_URL          = 'http://localhost:8082';
    //public static readonly ZUUL_URL          = 'http://ISD-PC0L38DR:8082';

    // Eureka Microservice Zuul Route Names
    //public static readonly MODS_SERVICE_ID          = "mods-api";
    public static readonly USER_ZUUL_ROUTE          = "user-api";
    public static readonly ADMIN_ZUUL_ROUTE         = "admin-api";
    public static readonly TRAINING_ZUUL_ROUTE      = "training-api";
    public static readonly MENTORSKILL_ZUUL_ROUTE  = "mentorskill-api";
    public static readonly PAYMENT_ZUUL_ROUTE       = "payment-api";
    
    // Zuul Root Paths
    public static readonly USER_API_ROOT_URL        = `${ApiURL.ZUUL_URL}/${ApiURL.USER_ZUUL_ROUTE}`;
    public static readonly ADMIN_API_ROOT_URL       = `${ApiURL.ZUUL_URL}/${ApiURL.ADMIN_ZUUL_ROUTE}`;
    public static readonly TRAINING_API_ROOT_URL    = `${ApiURL.ZUUL_URL}/${ApiURL.TRAINING_ZUUL_ROUTE}`;
    public static readonly MENTORSKILL_API_ROOT_URL = `${ApiURL.ZUUL_URL}/${ApiURL.MENTORSKILL_ZUUL_ROUTE}`;
    public static readonly PAYMENT_API_ROOT_URL     = `${ApiURL.ZUUL_URL}/${ApiURL.PAYMENT_ZUUL_ROUTE}`;

    // User Authentication URL
    public static readonly AUTH_LOGIN               = `${ApiURL.USER_API_ROOT_URL}/token/generate-token`;
    public static readonly AUTH_SIGNUP              = `${ApiURL.USER_API_ROOT_URL}/signup`;

    // ?????XXXX
    public static readonly AUTH_SEND_REG_CODE_MAIL  = `${ApiURL.USER_API_ROOT_URL}/signup/send_code`;

    // User
    public static readonly USER                     = `${ApiURL.USER_API_ROOT_URL}/users`;
    public static readonly USER_BY_ID               = `${ApiURL.USER_API_ROOT_URL}/users`;
    
    //?????
    public static readonly USER_HOME                = `${ApiURL.USER}/home`;
    public static readonly USER_UPDATE_PROFILE      = `${ApiURL.USER}/update`;
    public static readonly USER_GET_PROFILE         = `${ApiURL.USER}/profile`;

    // Home ????????
    public static readonly HOME                     = `${ApiURL.MENTORSKILL_API_ROOT_URL}/home`;
    public static readonly HOME_SEARCH_MENTOR       = `${ApiURL.HOME}/searchmentor`;
    public static readonly HOME_SEARCH_MENTOR_PROFILE = `${ApiURL.HOME}/view_mentor_profile`;

    // Skill
    public static readonly SKILLS                   = `${ApiURL.MENTORSKILL_API_ROOT_URL}/skills`;
    public static readonly SKILL_BY_ID              = `${ApiURL.MENTORSKILL_API_ROOT_URL}/skill`;

    // Mentor Skill
    public static readonly MENTOR_SKILLS            = `${ApiURL.MENTORSKILL_API_ROOT_URL}/mentor_skills`;
    public static readonly MENTOR_SKILLS_SAVE       = `${ApiURL.MENTOR_SKILLS}/save`;
    public static readonly MENTOR_SKILLS_EDIT       = `${ApiURL.MENTOR_SKILLS}/edit`;
    public static readonly MENTOR_SKILL_DELETE	    = `${ApiURL.MENTOR_SKILLS}/delete`;
    public static readonly MENTOR_NOT_SKILLS        = `${ApiURL.MENTOR_SKILLS}/not_mentor_skills`;

    // Mentor Calendar
    public static readonly MENTOR_CAL               = `${ApiURL.MENTORSKILL_API_ROOT_URL}/mentor_cals`;
    public static readonly MENTOR_CAL_SAVE          = `${ApiURL.MENTOR_CAL}/save`;
    public static readonly MENTOR_CAL_EDIT          = `${ApiURL.MENTOR_CAL}/edit`;
    public static readonly MENTOR_CAL_DELETE        = `${ApiURL.MENTOR_CAL}/delete`;

    // Training
    public static readonly TRAINING                 = `${ApiURL.TRAINING_API_ROOT_URL}/training`;
    public static readonly TRAINING_PROPOSE         = `${ApiURL.TRAINING}/propose`;
    public static readonly TRAINING_CONFIRM         = `${ApiURL.TRAINING}/confirm`;
    public static readonly TRAINING_FINALIZE        = `${ApiURL.TRAINING}/finalize`;
    public static readonly TRAINING_BY_USER         = `${ApiURL.TRAINING}/user`;
    public static readonly TRAINING_BY_MENTOR       = `${ApiURL.TRAINING}/mentor`;

    // Admin
    public static readonly ADMIN                        = `${ApiURL.ADMIN_API_ROOT_URL}/admin`;
    public static readonly ADMIN_BLOCK_USER             = `${ApiURL.ADMIN}/blockuser`;
    public static readonly ADMIN_GET_USERS              = `${ApiURL.ADMIN}/users`;
    public static readonly ADMIN_SKILL_ADD              = `${ApiURL.ADMIN}/skill/add`;
    public static readonly ADMIN_SKILL_EDIT             = `${ApiURL.ADMIN}/skill/edit`;
    public static readonly ADMIN_SKILL_DELETE           = `${ApiURL.ADMIN}/skill/delete`;
    public static readonly ADMIN_SETTINGS               = `${ApiURL.ADMIN}/settings`;
    public static readonly ADMIN_SETTINGS_GET_ALL       = `${ApiURL.ADMIN_SETTINGS}/all`;
    public static readonly ADMIN_SETTINGS_GET_BY_ID     = `${ApiURL.ADMIN_SETTINGS}/id`;
    public static readonly ADMIN_SETTINGS_GET_BY_NAME   = `${ApiURL.ADMIN_SETTINGS}/name`;
    public static readonly ADMIN_SETTINGS_ADD           = `${ApiURL.ADMIN_SETTINGS}/add`;
    public static readonly ADMIN_SETTINGS_EDIT          = `${ApiURL.ADMIN_SETTINGS}/edit`;
    public static readonly ADMIN_SETTINGS_DELETE        = `${ApiURL.ADMIN_SETTINGS}/delete`;

    // Payment
    public static readonly PAYMENT                      = `${ApiURL.PAYMENT_API_ROOT_URL}/payment`;
    public static readonly PAYMENT_GET_ALL              = `${ApiURL.PAYMENT_API_ROOT_URL}/payment/all`;

    // Mentor
    public static readonly MENTOR                      = `${ApiURL.MENTORSKILL_API_ROOT_URL}/mentor`;
    public static readonly MENTOR_GET_BY_USER_ID       = `${ApiURL.MENTOR}/userid`;
    public static readonly MENTOR_SAVE                 = `${ApiURL.MENTOR}/save`;
    public static readonly MENTOR_EDIT                 = `${ApiURL.MENTOR}/edit`;

}