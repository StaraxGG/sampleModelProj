package Model;

/**
 * An implementation of MasterData
 * in samplemodelproject
 *
 * @author ytatar
 * @version 1.0
 * @since 2019-Mar-24
 */
public class MasterData {

    /* ---------------------------------------- Main ---------------------------------------------------------------- */

    /* ---------------------------------------- Attributes ---------------------------------------------------------- */
    protected boolean persisted;
    /* ---------------------------------------- Constants ----------------------------------------------------------- */

    /* ---------------------------------------- Constructors -------------------------------------------------------- */

    /* ---------------------------------------- Methods ------------------------------------------------------------- */

    /* ---------------------------------------- S/Getters ----------------------------------------------------------- */
    public boolean isPersisted(){
        return this.persisted;
    }

    protected void setPersisted(boolean persisted){
        this.persisted = persisted;
    }
}

