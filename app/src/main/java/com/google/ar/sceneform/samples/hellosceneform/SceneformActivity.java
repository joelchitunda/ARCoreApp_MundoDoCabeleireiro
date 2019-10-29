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
    // já que queremos que nosso modelo 3d (objeto) seja renderizado sobre nossa imagem uma ÚNICA vez.
    //{ENG}	• We'll be adding a counter to identify each one of our augmentedImages since we
    //want our 3d models to be rendered on top of our augmented images only  ONCE.
    int imgCont1 = 0;
    int imgCont2 = 0;
    int imgCont3 = 0;
    int imgCont4 = 0;
    int imgCont5 = 0;
    boolean visto1 = false;
    boolean visto2 = false;
    boolean textSeen1 = false;

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
    private void placeObject1(ArFragment arFragment, Anchor anchor, Uri uri) {
        ModelRenderable.builder()
                .setSource(arFragment.getContext(), uri)
                .build()
                .thenAccept(modelRenderable -> addNodeToScene1(arFragment, anchor, modelRenderable))
                .exceptionally(throwable -> {
                            Toast.makeText(arFragment.getContext(), "Error:" + throwable.getMessage(), Toast.LENGTH_LONG).show();
                            return null;
                        }

                );

    }

    // TODO - Mudar o objeto 3d do mundo pra uma cartinha (Envelope), fazer o menu dos times
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void placeObject2(ArFragment arFragment, Anchor anchor, Uri uri) {
        ModelRenderable.builder()
                .setSource(arFragment.getContext(), uri)
                .build()
                .thenAccept(modelRenderable -> addNodeToScene2(arFragment, anchor, modelRenderable))
                .exceptionally(throwable -> {
                            Toast.makeText(arFragment.getContext(), "Error:" + throwable.getMessage(), Toast.LENGTH_LONG).show();
                            return null;
                        }

                );

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void placeObject3(ArFragment arFragment, Anchor anchor, Uri uri) {
        ModelRenderable.builder()
                .setSource(arFragment.getContext(), uri)
                .build()
                .thenAccept(modelRenderable -> addNodeToScene3(arFragment, anchor, modelRenderable))
                .exceptionally(throwable -> {
                            Toast.makeText(arFragment.getContext(), "Error:" + throwable.getMessage(), Toast.LENGTH_LONG).show();
                            return null;
                        }

                );

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void placeObject4(ArFragment arFragment, Anchor anchor, Uri uri) {
        ModelRenderable.builder()
                .setSource(arFragment.getContext(), uri)
                .build()
                .thenAccept(modelRenderable -> addNodeToScene4(arFragment, anchor, modelRenderable))
                .exceptionally(throwable -> {
                            Toast.makeText(arFragment.getContext(), "Error:" + throwable.getMessage(), Toast.LENGTH_LONG).show();
                            return null;
                        }

                );

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void placeObject5(ArFragment arFragment, Anchor anchor, Uri uri) {
        ModelRenderable.builder()
                .setSource(arFragment.getContext(), uri)
                .build()
                .thenAccept(modelRenderable -> addNodeToScene5(arFragment, anchor, modelRenderable))
                .exceptionally(throwable -> {
                            Toast.makeText(arFragment.getContext(), "Error:" + throwable.getMessage(), Toast.LENGTH_LONG).show();
                            return null;
                        }

                );

    }


    //Implementaremos um addNode referente a cada Imagem aumentada e Esse método conterá as especificações de tamanho do
    // modelo 3d a ser adicionado assim como o evento que irá ser realizado por meio de um toque no nó.
    private void addNodeToScene1(ArFragment arFragment, Anchor anchor, Renderable renderable) {
        TransformableNode node = new TransformableNode(arFragment.getTransformationSystem());
        AnchorNode anchorNode = new AnchorNode(anchor);
        //[PT-BR] • Usando o método (getScaleController) podemos mudar o tamanho com o qual o modelo 3d aparecerá na tela , caso ele seja muito grande ou muito pequeno.
        //[ENG] • The next two lines of code below show how it is possible to change the size of our 3d object , in case it is too big or too small.
        node.getScaleController().setMaxScale(0.0072f);
        node.getScaleController().setMinScale(0.0055f);

        node.setRenderable(renderable);
        node.setParent(anchorNode);
        arFragment.getArSceneView().getScene().addChild(anchorNode);
        node.select();
        node.setOnTouchListener((hitTestResult, motionEvent) -> {
            //[PT-BR] • Nesse caso a nossa atividade será mostrar uma mensagem (Toast)na tela , isso poderá ser mudado
            //[ENG] • In this case our activity consists in showing a message (Toast)
            Toast.makeText(arFragment.getContext(), "IMAGEM 1 - COLOQUE A DICA PARA A SEGUNDA IMAGEM AQUI", Toast.LENGTH_LONG).show();


            //[PT-BR] • Para evitar bugs com os objetos 3d , vamos removê-los da cena após o toque e a realização da atividade
            //[ENG] • To avoid bugs regarding our 3d objects,we'll remove them after the touch and the activity occur
            arFragment.getArSceneView().getScene().removeChild(anchorNode);
            anchorNode.getAnchor().detach();

            return true;
        });

    }


    private void addNodeToScene2(ArFragment arFragment, Anchor anchor, Renderable renderable) {
        TransformableNode node = new TransformableNode(arFragment.getTransformationSystem());
        AnchorNode anchorNode = new AnchorNode(anchor);

        node.getScaleController().setMaxScale(0.25f);
        node.getScaleController().setMinScale(0.24f);

        node.setRenderable(renderable);
        node.setParent(anchorNode);
        arFragment.getArSceneView().getScene().addChild(anchorNode);
        node.select();

        node.setOnTouchListener((hitTestResult, motionEvent) -> {

            Toast.makeText(arFragment.getContext(), "IMAGEM 2 - COLOQUE A DICA PARA A TERCEIRA IMAGEM AQUI", Toast.LENGTH_LONG).show();


            arFragment.getArSceneView().getScene().removeChild(anchorNode);
            anchorNode.getAnchor().detach();

            return true;
        });

    }


    private void addNodeToScene3(ArFragment arFragment, Anchor anchor, Renderable renderable) {
        TransformableNode node = new TransformableNode(arFragment.getTransformationSystem());
        AnchorNode anchorNode = new AnchorNode(anchor);
        node.getScaleController().setMaxScale(0.1f);
        node.getScaleController().setMinScale(0.025f);

        node.setRenderable(renderable);
        node.setParent(anchorNode);
        arFragment.getArSceneView().getScene().addChild(anchorNode);
        node.select();
        node.setOnTouchListener((hitTestResult, motionEvent) -> {

            Toast.makeText(arFragment.getContext(), "IMAGEM 3 - COLOQUE A DICA PARA A QUARTA IMAGEM AQUI", Toast.LENGTH_LONG).show();


            arFragment.getArSceneView().getScene().removeChild(anchorNode);
            anchorNode.getAnchor().detach();
            return true;

        });


    }

    private void addNodeToScene4(ArFragment arFragment, Anchor anchor, Renderable renderable) {
        TransformableNode node = new TransformableNode(arFragment.getTransformationSystem());
        AnchorNode anchorNode = new AnchorNode(anchor);
        node.getScaleController().setMaxScale(0.05f);
        node.getScaleController().setMinScale(0.025f);

        node.setRenderable(renderable);
        node.setParent(anchorNode);
        arFragment.getArSceneView().getScene().addChild(anchorNode);
        node.select();
        node.setOnTouchListener((hitTestResult, motionEvent) -> {

            Toast.makeText(arFragment.getContext(), "IMAGEM 4 - COLOQUE A DICA PARA A QUINTA IMAGEM AQUI", Toast.LENGTH_LONG).show();


            arFragment.getArSceneView().getScene().removeChild(anchorNode);
            anchorNode.getAnchor().detach();
            return true;

        });


    }

    private void addNodeToScene5(ArFragment arFragment, Anchor anchor, Renderable renderable) {
        TransformableNode node = new TransformableNode(arFragment.getTransformationSystem());
        AnchorNode anchorNode = new AnchorNode(anchor);
        node.getScaleController().setMaxScale(0.25f);
        node.getScaleController().setMinScale(0.025f);

        node.setRenderable(renderable);
        node.setParent(anchorNode);
        arFragment.getArSceneView().getScene().addChild(anchorNode);
        node.select();
        node.setOnTouchListener((hitTestResult, motionEvent) -> {

            Toast.makeText(arFragment.getContext(), "IMAGEM 5 - PARABÉNS! VOCÊ CONSEGUIU CHEGAR NA ÚLTIMA IMAGEM!", Toast.LENGTH_LONG).show();


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
        //[PT-BR] • Esse é o loop que checa constantemente se as imagens no nosso banco de dadoes estão sendo vistas pela
        // câmera na vida real. Caso alguma dessas imagens seja identificada na vida real, e esteja sendo vista de acordo
        // com a nossa rota prefenida ,mostraremos um objeto 3d na tela posicionado (ancorado) sobre essa imagem.
        //[ENG] • This loop is constantly comparing every frame seen in the real world (by our phone camera) with the
        // images contained in our augmented images Database. When an image caught by our camera matches any image in our
        // database and that image is seen in the correct order , a 3d object is going to be placed on top
        // of that image (This is supposed to only happen ONCE per image - using the method placeOBject).

        for (AugmentedImage augmentedImage : augmentedImages) {
            if (augmentedImage.getTrackingState() == TrackingState.TRACKING) {

                //[PT-BR] • Cada um dos if/else's a seguir checa uma imagem aumentada diferente contida no nosso banco de dados.
                //E cada if/else aninhado verifica se a rota de imagens foi cumprida (Se nenhuma imagem foi pulada e/ou vista fora de ordem).
                //[ENG] • Each one of the if statements below check a certain image included in our database. And every nested if/else
                // statements check if the images were captured in the correct order (Meaning that no image has been skipped).
                if (augmentedImage.getName().equals("muriloCard") && imgCont1==0) {
                    placeObject1(arFragment, augmentedImage.createAnchor(augmentedImage.getCenterPose()), Uri.parse("models/MagnifyingGlass.sfb"));
                    imgCont1++;
                }
                if (augmentedImage.getName().equals("earthAugmented") && (imgCont2==0)) {
                    if (textSeen1 == true && (imgCont1!=0)){
                        placeObject2(arFragment, augmentedImage.createAnchor(augmentedImage.getCenterPose()), Uri.parse("models/Present.sfb"));
                        imgCont2++;
                    }else if (textSeen1 == false) {
                        Toast.makeText(arFragment.getContext(), "Você está na imagem terra. Você está na imagem errada.", Toast.LENGTH_LONG).show();
                        textSeen1 = true;
                    }

                }

                /*  else if (augmentedImage.getName().equals("hairFly") && (imgCont3 == 0)) {
                    if((imgCont1==1)&&(imgCont2==1)&&(imgCont4==0)&&(imgCont5==0)) {
                        placeObject3(arFragment, augmentedImage.createAnchor(augmentedImage.getCenterPose()), Uri.parse("models/Present.sfb"));
                        imgCont3++;
                    }
                    else{
                        Toast.makeText(arFragment.getContext(), "Você está na imagem errada.", Toast.LENGTH_LONG).show();

                    }

                }  else if (augmentedImage.getName().equals("daniArt") && (imgCont4 == 0)) {
                    if((imgCont1==1)&&(imgCont2==1)&&(imgCont3==1)&&(imgCont5==0)) {
                        placeObject4(arFragment, augmentedImage.createAnchor(augmentedImage.getCenterPose()), Uri.parse("models/PerfumeBottle.sfb"));
                        imgCont4++;
                    }
                    else {
                        Toast.makeText(arFragment.getContext(), "Você está na imagem errada.", Toast.LENGTH_LONG).show();

                    }

                } else if (augmentedImage.getName().equals("loveMundo") && (imgCont5 == 0)){
                    if((imgCont1==1)&&(imgCont2==1)&&(imgCont3==1)&&(imgCont4==1)) {
                        placeObject5(arFragment, augmentedImage.createAnchor(augmentedImage.getCenterPose()), Uri.parse("models/andy.sfb"));
                        imgCont5++;
                    }
                    else{
                        Toast.makeText(arFragment.getContext(), "Você está na imagem errada.", Toast.LENGTH_LONG).show();

                    }
                } */
            }
        }
    }


}

