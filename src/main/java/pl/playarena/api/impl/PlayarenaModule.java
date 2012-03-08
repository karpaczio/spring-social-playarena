package pl.playarena.api.impl;

import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.module.SimpleModule;

/**
 */
public class PlayarenaModule extends SimpleModule {

    /**
     * Constructor for NkModule.
     */
    public PlayarenaModule() {
        super("PlayarenaModule", new Version(0, 0, 1, "SNAPSHOT"));
    }

    /**
     * Method setupModule.
     * 
     * @param context
     *            SetupContext
     */
    @Override
    public void setupModule(SetupContext context) {

        setMixInAnnotation(Profile.class, ProfileMixIn.class);

        super.setupModule(context);
    }

}
