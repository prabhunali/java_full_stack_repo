export class ApiURL {

    // Api Gateway (Zuul) URL
    public static readonly ZUUL_URL          = 'http://localhost:8082';

    // Eureka Application Name
    public static readonly MODS_SERVICE_ID         = "mods-api";

    // Authentication URL
    public static readonly AUTH_LOGIN               = `${ApiURL.ZUUL_URL}/token/generate-token`;
    public static readonly AUTH_SIGNUP              = `${ApiURL.ZUUL_URL}/signup`;
    public static readonly AUTH_SEND_REG_CODE_MAIL  = `${ApiURL.ZUUL_URL}/${ApiURL.MODS_SERVICE_ID}/signup/send_code`;

    // User
    public static readonly USER                     = `${ApiURL.ZUUL_URL}/${ApiURL.MODS_SERVICE_ID}/users`;
    public static readonly USER_BY_ID               = `${ApiURL.ZUUL_URL}/${ApiURL.MODS_SERVICE_ID}/users`;
    public static readonly USER_HOME                = `${ApiURL.USER}/home`;
    public static readonly USER_UPDATE_PROFILE      = `${ApiURL.USER}/update`;
    public static readonly USER_GET_PROFILE         = `${ApiURL.USER}/profile`;

    // Home
    public static readonly HOME                     = `${ApiURL.ZUUL_URL}/${ApiURL.MODS_SERVICE_ID}/home`;
    public static readonly HOME_SEARCH_MENTOR       = `${ApiURL.HOME}/searchmentor`;

    // Skill
    public static readonly SKILLS                   = `${ApiURL.ZUUL_URL}/${ApiURL.MODS_SERVICE_ID}/skills`;
    public static readonly SKILL_BY_ID              = `${ApiURL.ZUUL_URL}/${ApiURL.MODS_SERVICE_ID}/skill`;

    // Mentor Skill
    public static readonly MENTOR_SKILLS            = `${ApiURL.ZUUL_URL}/${ApiURL.MODS_SERVICE_ID}/mentor_skills`;
    public static readonly MENTOR_SKILLS_SAVE       = `${ApiURL.MENTOR_SKILLS}/save`;
    public static readonly MENTOR_SKILLS_EDIT       = `${ApiURL.MENTOR_SKILLS}/edit`;
    public static readonly MENTOR_SKILL_DELETE	    = `${ApiURL.MENTOR_SKILLS}/delete`;
    public static readonly MENTOR_NOT_SKILLS        = `${ApiURL.MENTOR_SKILLS}/not_mentor_skills`;

    // Mentor Calendar
    public static readonly MENTOR_CAL               = `${ApiURL.ZUUL_URL}/${ApiURL.MODS_SERVICE_ID}/mentor_cals`;
    public static readonly MENTOR_CAL_SAVE          = `${ApiURL.MENTOR_CAL}/save`;
    public static readonly MENTOR_CAL_EDIT          = `${ApiURL.MENTOR_CAL}/edit`;
    public static readonly MENTOR_CAL_DELETE        = `${ApiURL.MENTOR_CAL}/delete`;
}