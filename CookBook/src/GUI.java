import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class GUI{

    public GUI() {

        int frame_width = 750;
        int frame_height = 900;
        ArrayList<Recipe> recipes_list = new ArrayList<Recipe>();

        //Title label
        ImageIcon cooking = new ImageIcon("src/cooking.jpg");
        JLabel title_label = new JLabel("Welcome to your Cook Book!");
        title_label.setFont(new Font("MV Boli",Font.PLAIN,20));
        title_label.setIcon(cooking);
        title_label.setHorizontalTextPosition(JLabel.CENTER);
        title_label.setVerticalTextPosition(JLabel.TOP);
        title_label.setVerticalAlignment(JLabel.TOP);
        title_label.setHorizontalAlignment(JLabel.CENTER);


    //All Panels
        //title_panel

        JPanel title_panel = new JPanel();
        title_panel.add(title_label);
        title_panel.setBackground(Color.cyan);
        title_panel.setPreferredSize(new Dimension(100,250));

        //button Panel
        JPanel button_panel = new JPanel();
        button_panel.setBackground(new Color(250, 128, 114));
        button_panel.setLayout(new GridLayout(2,0,50,50));
        button_panel.setBorder(BorderFactory.createEmptyBorder(60,60,20,60));


    //All Buttons
        //Add recipe Button
        JButton add_recipe_button = new JButton("Add Recipe");
        add_recipe_button.addActionListener(e -> {
            add_recipe_window(recipes_list);
        });
        add_recipe_button.setBounds(80,40,100,100);

        //View recipe Button
        JButton view_recipe_button = new JButton("View Recipe");
        view_recipe_button.addActionListener(e -> {
            view_recipe_window(recipes_list);
        });
        view_recipe_button.setSize(80,40);

    //All Frames
        //Home frame
        JFrame home_frame = new JFrame();
        home_frame.setLayout(new BorderLayout());
        home_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        home_frame.setSize(frame_width,frame_height);
        home_frame.setVisible(true);

    //Add Statements
        home_frame.add(title_panel,BorderLayout.NORTH);
        home_frame.add(button_panel);
        button_panel.add(add_recipe_button);
        button_panel.add(view_recipe_button);



    }
    public void view_recipe_window(ArrayList<Recipe> recipeArrayList) {
        recipeArrayList.forEach(recipe -> {
            System.out.println(recipe.getName());
            recipe.getIngredients().forEach(ingredient -> {
                System.out.println(ingredient.getName());
            });
            recipe.getInstructions();

        });

        JFrame view_recipe_window = new JFrame("View Recipe");
        view_recipe_window.setSize(420,420);
        view_recipe_window.setLayout(null);
        view_recipe_window.setVisible(true);
        view_recipe_window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel selection_text = new JLabel("Select Your Recipe");
        selection_text.setBounds(50,20,300,30);
        view_recipe_window.add(selection_text);

        JComboBox selection = new JComboBox();
        selection.addItem(" ");
        selection.addActionListener(e -> {
            String recipe_name = (String)selection.getSelectedItem();



            recipeArrayList.forEach(recipe -> {
                if (recipe.getName() == recipe_name) {
                    JFrame recipe_window = new JFrame(recipe_name);
                    recipe_window.setSize(600,800);
                    recipe_window.setLayout(null);
                    recipe_window.setVisible(true);
                    recipe_window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    String name = recipe.getName();

                    JLabel recipe_header = new JLabel("Recipe: ");
                    recipe_header.setBounds(30,0,120,30);
                    recipe_header.setFont(new Font(null, Font.BOLD, 20));
                    recipe_window.add(recipe_header);

                    JLabel recipe_title = new JLabel(recipe.getName());
                    recipe_title.setBounds(150,0,300,30);
                    recipe_window.add(recipe_title);

                    JLabel ingredients_title = new JLabel("Ingredients:");
                    ingredients_title.setFont(new Font(null, Font.BOLD, 20));
                    ingredients_title.setBounds(30,50,300,30);
                    recipe_window.add(ingredients_title);


                    AtomicInteger new_ingredient_y = new AtomicInteger(80);
                    recipe.getIngredients().forEach(ingredient -> {

                        String ingredient_name = ingredient.getName();
                        String qty = ingredient.getQuantity();
                        String unit = ingredient.getMeasurement_unit();

                        new_ingredient_y.addAndGet(25);
                        JTextField new_ingredient = new JTextField(qty + " " + unit + " " + ingredient_name);
                        new_ingredient.setBounds(30,new_ingredient_y.get(),300,20);
                        new_ingredient.setEditable(false);
                        recipe_window.add(new_ingredient);
                    });

                    JLabel instruction_header = new JLabel("Cooking Directions");
                    instruction_header.setFont(new Font(null, Font.BOLD, 20));
                    instruction_header.setBounds(30,340,300,30);
                    recipe_window.add(instruction_header);

                    JTextArea instructions = new JTextArea(recipe.getInstructions());
                    instructions.setLineWrap(true);
                    instructions.setWrapStyleWord(true);
                    instructions.setEditable(false);
                    instructions.setBounds(30,375,530,350);
                    recipe_window.add(instructions);



                }
            });

        });
        recipeArrayList.forEach(recipe -> {
            selection.addItem(recipe.getName());
        });
        selection.setBounds(50,50,300,30);
        selection.setEditable(true);
        view_recipe_window.add(selection);


    }

    public void add_recipe_window(ArrayList<Recipe> recipeArrayList) {

        ArrayList<Ingredient> ingredients_list = new ArrayList<Ingredient>();

        JFrame add_recipe_window = new JFrame("Add Recipe");
        add_recipe_window.setLayout(null);
        GridBagConstraints c = new GridBagConstraints();
        add_recipe_window.setSize(700,900);
        add_recipe_window.setResizable(false);
        add_recipe_window.setVisible(true);
        add_recipe_window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

//Recipe name
        JLabel recipe_name = new JLabel("Recipe Name");
        recipe_name.setBounds(50,0,300,30);
        add_recipe_window.add(recipe_name);

        JTextField recipe_name_text_field = new JTextField();
        recipe_name_text_field.setBounds(50,25,300,30);
        add_recipe_window.add(recipe_name_text_field);

//Add Ingredient
        JLabel ingredient_name = new JLabel("Ingredient Name");
        ingredient_name.setBounds(50,50,300,30);
        add_recipe_window.add(ingredient_name);

        JTextField ingredient_text_field = new JTextField();
        ingredient_text_field.setBounds(50,75,300,30);
        add_recipe_window.add(ingredient_text_field);

        JLabel ingredient_qty = new JLabel("Quantity");
        ingredient_qty.setBounds(370,50,50,30);
        add_recipe_window.add(ingredient_qty);

        JTextField qty_text = new JTextField();
        qty_text.setBounds(370,75,50,30);
        add_recipe_window.add(qty_text);

        JLabel unit_of_measurement = new JLabel("Measurement ie. cups, ounces");
        unit_of_measurement.setBounds(440,50,200,30);
        add_recipe_window.add(unit_of_measurement);

        JTextField unit_text = new JTextField();
        unit_text.setBounds(440,75,150,30);
        add_recipe_window.add(unit_text);

        JButton add_ingredient = new JButton("Add");
        add_ingredient.setBounds(600,75,70,30);
        add_recipe_window.add(add_ingredient);
        AtomicInteger ingredient_y = new AtomicInteger(100);
        add_ingredient.addActionListener(e -> {
            String name = ingredient_text_field.getText();
            String qty = qty_text.getText();
            String unit = unit_text.getText();

            ingredient_y.addAndGet(20);
            JTextField new_ingredient = new JTextField(qty + " " + unit + " " + name);
            new_ingredient.setBounds(50,ingredient_y.get(),300,20);
            new_ingredient.setEditable(false);
            add_recipe_window.add(new_ingredient);

            ingredients_list.add(new Ingredient(name,qty,unit));

        });


//Text instructions Area
        JLabel recipe_Instructions = new JLabel("Recipe Instructions");
        recipe_Instructions.setBounds(50,470,300,30);
        add_recipe_window.add(recipe_Instructions);

        JTextArea recipe_instructions_text_field = new JTextArea();
        recipe_instructions_text_field.setLineWrap(true);
        recipe_instructions_text_field.setWrapStyleWord(true);

        JScrollPane scroll_instructions = new JScrollPane(recipe_instructions_text_field);
        scroll_instructions.setBounds(50,500,600,300);
        scroll_instructions.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        add_recipe_window.add(scroll_instructions);

//Submit Recipe Button

        JButton recipe_submit = new JButton("Submit");
        recipe_submit.setBounds(325,820,100,30);
        add_recipe_window.add(recipe_submit);

        recipe_submit.addActionListener(e -> {
            String name = recipe_name_text_field.getText();
            String directions = recipe_instructions_text_field.getText();

            recipeArrayList.add(new Recipe(name,ingredients_list,directions));
            add_recipe_window.dispose();
        });




    }
}
