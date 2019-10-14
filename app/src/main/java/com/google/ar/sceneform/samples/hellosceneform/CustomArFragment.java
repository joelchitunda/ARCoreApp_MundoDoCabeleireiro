package com.google.ar.sceneform.samples.hellosceneform;

import android.content.res.AssetManager;
import android.util.Log;

import com.google.ar.core.AugmentedImageDatabase;
import com.google.ar.core.Config;
import com.google.ar.core.Session;
import com.google.ar.sceneform.ux.ArFragment;

import java.io.IOException;
import java.io.InputStream;

public class CustomArFragment extends ArFragment {
    private static final String SAMPLE_IMAGE_DATABASE = "myimages.imgdb";

    @Override
    protected Config getSessionConfiguration(Session session) {
        getPlaneDiscoveryController().setInstructionView(null);
        Config config = new Config(session);
        config.setUpdateMode(Config.UpdateMode.LATEST_CAMERA_IMAGE);
        session.configure(config);
        getArSceneView().setupSession(session);
        if (setupAugmentedImageDatabase(config, session)) {
            Log.d("SetupAugImgDb", "Success");
        } else {
            Log.e("SetupAugImgDb", "Failure setting up db");
        }
        return config;
    }

    //Esse método prepara o nosso banco de dados de imagens aumentadas - This method sets up our augmentedImages Database
    public boolean setupAugmentedImageDatabase(Config config, Session session) {
        AugmentedImageDatabase augmentedImageDatabase;
        config.setFocusMode(Config.FocusMode.AUTO);

        AssetManager assetManager = getContext() != null ? getContext().getAssets() : null;
        if (assetManager == null) {
            Log.e("AugmentedImageFragmemt", "Context is null, cannot intitialize image database.");
            return false;
        } else {
            // This is an alternative way to initialize an AugmentedImageDatabase instance,
            // load a pre-existing augmented image database.
            try (InputStream is = getContext().getAssets().open(SAMPLE_IMAGE_DATABASE)) {
                augmentedImageDatabase = AugmentedImageDatabase.deserialize(session, is);
            } catch (IOException e) {
                Log.e("AugmentedImageFragment", "IO exception loading augmented image database.", e);
                return false;
            }
        }
        config.setAugmentedImageDatabase(augmentedImageDatabase);
        return true;
    }
}