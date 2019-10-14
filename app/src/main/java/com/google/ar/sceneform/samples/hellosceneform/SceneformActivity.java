package com.google.ar.sceneform.samples.hellosceneform;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.google.ar.core.Anchor;
import com.google.ar.core.AugmentedImage;
import com.google.ar.core.Frame;
import com.google.ar.core.TrackingState;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.FrameTime;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.Renderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

import java.util.Collection;


public class SceneformActivity extends AppCompatActivity {

    ArFragment arFragment;

    //[PT-BR] • Adicionaremos um contador para identificar cada augmentedIMG (imagem de realidade aumentada)
    // já que queremos que nosso modelo 3d seja renderizado sobre nossa imagem uma ÚNICA vez
    //{ENG}	• We'll be adding a counter to identify each one of our augmentedImages since we
    //want our 3d models to be rendered on top of our augmented images only  ONCE.
    int imgCont1 = 0;
    int imgCont2 = 0;
    int imgCont3 = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity);
        arFragment = (CustomArFragment) getSupportFragmentManager().findFragmentById(R.id.sceneform_fragment);
        arFragment.getPlaneDiscoveryController().hide();
        arFragment.getPlaneDiscoveryController().setInstructionView(null);
        arFragment.getArSceneView().getPlaneRenderer().setEnabled(false);
        arFragment.getArSceneView().getScene().addOnUpdateListener(this::onUpdateFrame);
        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.sceneform_fragment);
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    //[PT-BR] • Método que posiciona o modelo 3d sobre a imagemAumentada
    //[ENG] • Method that places our 3D object on top of our augmented image
    //TODO - Implementaremos um plceObject difente para cada Imagem aumentada e
    // esse método adicionará o modelo 3d específico requerido passado pelo addNOde
    private void placeObjectMurilo(ArFragment arFragment, Anchor anchor, Uri uri) {
        ModelRenderable.builder()
                .setSource(arFragment.getContext(), uri)
                .build()
                .thenAccept(modelRenderable -> addNodeToSceneMurilo(arFragment, anchor, modelRenderable))
                .exceptionally(throwable -> {
                            Toast.makeText(arFragment.getContext(), "Error:" + throwable.getMessage(), Toast.LENGTH_LONG).show();
                            return null;
                        }

                );

    }

    //[PT-BR] • Método que posiciona o modelo 3d sobre a imagemAumentada
    //[ENG] • Method that places our 3D object on top of our augmented image
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void placeObjectTerra(ArFragment arFragment, Anchor anchor, Uri uri) {
        ModelRenderable.builder()
                .setSource(arFragment.getContext(), uri)
                .build()
                .thenAccept(modelRenderable -> addNodeToSceneTerra(arFragment, anchor, modelRenderable))
                .exceptionally(throwable -> {
                            Toast.makeText(arFragment.getContext(), "Error:" + throwable.getMessage(), Toast.LENGTH_LONG).show();
                            return null;
                        }

                );

    }

    //[PT-BR] • Método que posiciona o modelo 3d sobre a imagemAumentada
    //[ENG] • Method that places our 3D object on top of our augmented image
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void placeObjectDani(ArFragment arFragment, Anchor anchor, Uri uri) {
        ModelRenderable.builder()
                .setSource(arFragment.getContext(), uri)
                .build()
                .thenAccept(modelRenderable -> addNodeToSceneDani(arFragment, anchor, modelRenderable))
                .exceptionally(throwable -> {
                            Toast.makeText(arFragment.getContext(), "Error:" + throwable.getMessage(), Toast.LENGTH_LONG).show();
                            return null;
                        }

                );

    }

    //TODO - Implementaremos um addNode referente a cada Imagem aumentada e Esse método conterá as especificações de tamanho do modelo 3d a ser adicionado
    // assim como o evento que irá ser realizado por meio de um toque no nó

    private void addNodeToSceneMurilo(ArFragment arFragment, Anchor anchor, Renderable renderable) {
        TransformableNode node = new TransformableNode(arFragment.getTransformationSystem());
        AnchorNode anchorNode = new AnchorNode(anchor);
        //[PT-BR] • Aqui podemos mudar o tamanho com o qual o modelo 3d aparecerá na tela , caso ele seja muito grande ou muito pequeno
        //[ENG] • The next two lines of code below show how it is possible to change the size of our 3d object , in case it is too big or too small
        node.getScaleController().setMaxScale(0.20f);
        node.getScaleController().setMinScale(0.15f);

        node.setRenderable(renderable);
        node.setParent(anchorNode);
        arFragment.getArSceneView().getScene().addChild(anchorNode);
        node.select();
        node.setOnTouchListener((hitTestResult, motionEvent) -> {
            //[PT-BR] • Nesse caso a nossa atividade será abrir um site , isso poderá ser mudado
            //[ENG] • In this case our activity consists in opening a website
            openWebsite1(arFragment.getView());

            return true;
        });

    }

    private void addNodeToSceneTerra(ArFragment arFragment, Anchor anchor, Renderable renderable) {
        TransformableNode node = new TransformableNode(arFragment.getTransformationSystem());
        AnchorNode anchorNode = new AnchorNode(anchor);
        //[PT-BR] • Aqui podemos mudar o tamanho com o qual o modelo 3d aparecerá na tela , caso ele seja muito grande ou muito pequeno
        //[ENG] • The next two lines of code below show how it is possible to change the size of our 3d object , in case it is too big or too small
        node.getScaleController().setMaxScale(0.05f);
        node.getScaleController().setMinScale(0.025f);

        node.setRenderable(renderable);
        node.setParent(anchorNode);
        arFragment.getArSceneView().getScene().addChild(anchorNode);
        node.select();

        node.setOnTouchListener((hitTestResult, motionEvent) -> {
            //[PT-BR] • Nesse caso a nossa atividade será abrir um site , isso poderá ser mudado
            //[ENG] • In this case our activity consists in opening a website
            openWebsite2(arFragment.getView());

            return true;
        });

    }

    private void addNodeToSceneDani(ArFragment arFragment, Anchor anchor, Renderable renderable) {
        TransformableNode node = new TransformableNode(arFragment.getTransformationSystem());
        AnchorNode anchorNode = new AnchorNode(anchor);
        //[PT-BR] • Aqui podemos mudar o tamanho com o qual o modelo 3d aparecerá na tela , caso ele seja muito grande ou muito pequeno
        //[ENG] • The next two lines of code below show how it is possible to change the size of our 3d object , in case it is too big or too small
        node.getScaleController().setMaxScale(0.05f);
        node.getScaleController().setMinScale(0.025f);

        node.setRenderable(renderable);
        node.setParent(anchorNode);
        arFragment.getArSceneView().getScene().addChild(anchorNode);
        node.select();
        node.setOnTouchListener((hitTestResult, motionEvent) -> {
            //[PT-BR] • Nesse caso a nossa atividade será abrir um site , isso poderá ser mudado
            //[ENG] • In this case our activity consists in opening a website
            openWebsite3(arFragment.getView());
            arFragment.getArSceneView().getScene().removeChild(anchorNode);
            anchorNode.getAnchor().detach();
            return true;

        });


    }


    //[PT-BR] • Atividades
    // [ENG] • Activities
    public void openWebsite1(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/murilo-miranda-0039619/?originalSubdomain=br"));
        startActivity(browserIntent);
    }

    public void openWebsite2(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.mundodocabeleireiro.com.br/"));
        startActivity(browserIntent);
    }

    public void openWebsite3(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.hairfly.com.br/"));
        startActivity(browserIntent);
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void onUpdateFrame(FrameTime frameTime) {
        Frame frame = arFragment.getArSceneView().getArFrame();

        Collection<AugmentedImage> augmentedImages = frame.getUpdatedTrackables(AugmentedImage.class);
        //[PT-BR] • Esse é o loop que checa constantemente se as imagens no nosso banco de dadoes estão sendo vistas pela câmera na vida real
        // Caso a imagem desejada seja identificada na vida real , mostraremos um objeto 3d na tela posicionado (ancorado) sobre a imagem
        //[ENG] • This loop is constantly comparing every frame seen in the real world (by our phone camera) with the images contained in our
        // augmented images Database. When an image caught by our camera matches any image in our database , a 3d object is going to be placed on top
        // of that image (This is supposed to only happen ONCE per image - using the method placeOBject)
        for (AugmentedImage augmentedImage : augmentedImages) {
            if (augmentedImage.getTrackingState() == TrackingState.TRACKING) {
                //[PT-BR] • Cada um dos if's a seguir se refere a uma imagem aumentada diferente contida no nosso banco de dados
                //[ENG] • Each one of the if statements below refer to a certain image included in our database
                if (augmentedImage.getName().equals("muriloCard") && (imgCont1 == 0)) {
                    placeObjectMurilo(arFragment, augmentedImage.createAnchor(augmentedImage.getCenterPose()), Uri.parse("model.sfb"));
                    imgCont1++;


                } else if (augmentedImage.getName().equals("earthAugmented") && (imgCont2 == 0)) {
                    placeObjectTerra(arFragment, augmentedImage.createAnchor(augmentedImage.getCenterPose()), Uri.parse("YSL_Libre_bottle_v002.sfb"));
                    imgCont2++;

                } else if (augmentedImage.getName().equals("hairFly") && (imgCont3 == 0)) {
                    placeObjectDani(arFragment, augmentedImage.createAnchor(augmentedImage.getCenterPose()), Uri.parse("GuerlainBottle.sfb"));
                    imgCont3++;
                }
            }
        }
    }


}

