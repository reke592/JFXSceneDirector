package com.JFXSceneDirector;

import com.JFXSceneDirector.util.BindingGroup;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Hashtable;
import java.util.Map;

/**
 * A base class extension that provide some functionality for subclasses (scene controller)
 * */
public abstract class Director  {

    private static Map<String, ISceneModel> _scenes = null;
    private static Map<String, BindingGroup> _binding_groups = null;
    private static Stage _stage = null;
    private static SceneContext _context = null;
    private static String _start_scene = null;


    // fetch the controller class for classloader on class instantiation
    public Director() {
        _scenes = new Hashtable<>();
        _binding_groups = new Hashtable<>();
        SceneModel.setClassLoader(this.getClass().getClassLoader());
    }


    /**
     * Initialize Director
     * @param stage the application start stage
     * */
    public void init(Stage stage) {
        _stage = stage;

        if(_context == null)
            show(_start_scene);
        else
            show(_start_scene, _context);
    }

    /**
     * stop Director, remove all bindings, clear memory
     * */
    public static void close() {

        // don't use lambda
        for(String groupName : _binding_groups.keySet())
            removeBindingGroup(groupName);

        _binding_groups.clear();
        _scenes.clear();

        _scenes = null;
        _binding_groups = null;
        _stage = null;
        _context = null;
        _start_scene = null;
        SceneModel.setClassLoader(null);
    }


    /**
     * The first scene to show when Director.init() was called
     * @param start_scene a certain key value that exist in Director scenes
     * */
    protected void start(String start_scene) {
        _start_scene = start_scene;
    }


    /**
     * The first scene to show when Director.init() was called
     * @param start_scene a certain key value that exist in Director scenes
     * @param initialContext the value you want to pass into scene
     * */
    protected void start(String start_scene, SceneContext initialContext) {
        _start_scene = start_scene;
        _context = initialContext;
    }


    /**
     * Register new scene
     * */
    protected void add(String key, String fxml_source, String win_title) {
        _scenes.put(key, new SceneModel(fxml_source, win_title));
    }


    /**
     * Display scene
     * @param key scene key
     * */
    public static void show(String key) {
        show(key, null, null);
    }


    /**
     * Display scene with scene context
     * @param key scene key
     * @param context the value you want to pass into next scene
     * */
    public static void show(String key, SceneContext context) {
        show(key, null, context);
    }


    /**
     * Display scene to specific stage
     * @param key scene key
     * @param stage target stage to show the scene
     * */
    public static void show(String key, Stage stage) {
        show(key, stage, null);
    }


    /**
     * Display scene to specific stage with scene context
     * @param key scene key
     * @param stage target stage to show the scene
     * @param context the value you want to pass into next scene
     * */
    public static void show(String key, Stage stage, SceneContext context) {
        ISceneModel model = _scenes.get(key);

        /*
         set the context first, because when the stage.show() is called,
         the getContext() inside initialize method of Initializable implementor will be triggered
         that process will remove the context value in static memory.
         See : Director.getContext()
        */
        _context = context;


        // show the after setting the context stage
        if(stage != null) {
            stage.setScene(new Scene(model.load()));
            stage.setTitle(model.getWinTitle());
            stage.show();
        }
        else {
            _stage.setScene(new Scene(model.load()));
            _stage.setTitle(model.getWinTitle());
            _stage.show();
        }
    }



    /**
     * Get the stored context passed by the previous scene<br><br>
     * <em>once called, the Director context will become null to avoid future conflicts.</em>
     * */
    public static Object getContext() {
        Object r_context = null;

        if ( _context != null ) {
            r_context = _context.getContext();
            _context = null;
        }

        return r_context;
    }


    /**
     * create binding group that handles auto binding and unbinding between multiple fxml source.
     * @param group BindingGroup
     * @see com.JFXSceneDirector.util.BindingGroup com.JFXSceneDirector.util.BindingGroup
     * */
    public static void createBindingGroup(BindingGroup group){
        // remove previous bindings
        if(_binding_groups.containsKey(group.getName())) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    removeBindingGroup(group.getName());
                }
            });
        }

        _binding_groups.put(group.getName(), group);
    }

    /**
     * remove binding group, all the active bindings in group will be unbind.
     * @param group_name String
     * */
    public static void removeBindingGroup(String group_name) {
        _binding_groups.get(group_name).unbindAll();
        _binding_groups.remove(group_name);
    }

}
