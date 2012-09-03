package pl.playarena.api.impl;

import org.apache.shindig.protocol.RestfulCollection;
import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.module.SimpleModule;

import pl.nk.social.api.impl.RestfulCollectionMixIn;

/**
 */
public class PlayarenaModule extends SimpleModule {

    /**
     * Constructor for NkModule.
     */
    public PlayarenaModule() {
        super("PlayarenaModule", new Version(0, 0, 3, null));
    }

    /**
     * Method setupModule.
     * 
     * @param context
     *            SetupContext
     */
    @Override
    public void setupModule(SetupContext context) {

        //setMixInAnnotation(Profile.class, ProfileMixIn.class);
        context.setMixInAnnotations(Profile.class, ProfileMixIn.class);
        // setMixInAnnotation(RestfulCollection.class, RestfulCollectionMixIn.class);
        context.setMixInAnnotations(RestfulCollection.class, RestfulCollectionMixIn.class);

        super.setupModule(context);
    }

}
