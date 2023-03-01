package com.pek.cook

import com.google.api.ResourceProto.resource

object RecipeDatabase{

    val recipeList = listOf(
        Recipe(
            0,
            "Chicken Tikka Masala",
            "A classic Indian dish with marinated chicken in a spiced tomato-based sauce",
            listOf(
                "1 lb boneless, skinless chicken thighs, cut into bite-sized pieces",
                "1/2 cup plain Greek yogurt",
                "1 tablespoon garam masala",
                "1 teaspoon ground cumin",
                "1/2 teaspoon ground turmeric",
                "1/2 teaspoon ground coriander",
                "1/4 teaspoon cayenne pepper",
                "1 tablespoon vegetable oil",
                "1 large onion, chopped",
                "4 cloves garlic, minced",
                "1 tablespoon grated fresh ginger",
                "1 can (14 oz) crushed tomatoes",
                "1/2 cup heavy cream",
                "Salt and pepper, to taste",
                "Cooked rice, for serving"
            ),
            listOf(
                "In a large bowl, mix together the chicken, yogurt, garam masala, cumin, turmeric, coriander, and cayenne pepper until the chicken is evenly coated.",
                "Cover the bowl and refrigerate for at least 30 minutes or up to 8 hours.",
                "In a large skillet or wok, heat the vegetable oil over medium-high heat.",
                "Add the onion and cook until softened and lightly browned, about 5 minutes.",
                "Add the garlic and ginger and cook until fragrant, about 1 minute.",
                "Add the marinated chicken and cook until browned and cooked through, about 10 minutes.",
                "Add the crushed tomatoes and simmer for 10-15 minutes, until the sauce has thickened slightly.",
                "Stir in the heavy cream and season with salt and pepper to taste.",
                "Serve the chicken tikka masala over cooked rice."
            ),
            R.drawable.p1,
            "Western",
            10
        ),
        Recipe(
            1,
            "Spaghetti Bolognese",
            "Classic Italian dish with a rich tomato sauce and ground beef",
            listOf(
                "1 pound ground beef",
                "1 onion, chopped",
                "2 cloves garlic, minced",
                "1 can (28 oz) crushed tomatoes",
                "1 tablespoon tomato paste",
                "1 teaspoon dried basil",
                "1/2 teaspoon dried oregano",
                "1/4 teaspoon red pepper flakes",
                "Salt and pepper, to taste",
                "1 pound spaghetti",
                "Grated Parmesan cheese, for serving"
            ),
            listOf(
                "In a large skillet, cook the ground beef over medium heat until browned, stirring frequently.",
                "Add the onion and garlic and cook until the onion is softened, about 5 minutes.",
                "Stir in the crushed tomatoes, tomato paste, basil, oregano, red pepper flakes, salt, and pepper.",
                "Bring the sauce to a simmer and let cook for 20-30 minutes, stirring occasionally.",
                "Meanwhile, cook the spaghetti according to the package instructions until al dente.",
                "Drain the spaghetti and serve with the Bolognese sauce on top.",
                "Sprinkle with grated Parmesan cheese, if desired."
            ),
            R.drawable.p2,
            "Western",
            23
        ),
        Recipe(
            2,
            "Greek Salad",
            "A refreshing salad with Mediterranean flavors",
            listOf(
                "1 head of romaine lettuce, chopped",
                "1/2 red onion, thinly sliced",
                "1/2 cucumber, chopped",
                "1 bell pepper, chopped",
                "1/2 cup cherry tomatoes, halved",
                "1/4 cup Kalamata olives",
                "4 oz feta cheese, crumbled",
                "Salt and pepper, to taste",
                "1/4 cup olive oil",
                "2 tablespoons red wine vinegar",
                "1 teaspoon Dijon mustard",
                "1 garlic clove, minced"
            ),
            listOf(
                "In a large bowl, combine the lettuce, onion, cucumber, bell pepper, tomatoes, olives, and feta cheese.",
                "In a small bowl, whisk together the olive oil, red wine vinegar, mustard, garlic, salt, and pepper.",
                "Drizzle the dressing over the salad and toss to combine."
            ),
            R.drawable.p3,
            "Western",
            6
        ),
        Recipe(
            3,
            "Pesto Pasta",
            "A classic pasta dish with homemade pesto",
            listOf(
                "8 oz pasta",
                "2 cups fresh basil leaves",
                "1/2 cup grated Parmesan cheese",
                "1/2 cup olive oil",
                "3 garlic cloves, minced",
                "Salt and pepper, to taste"
            ),
            listOf(
                "Cook the pasta according to package instructions. Drain and set aside.",
                "In a food processor, combine the basil, Parmesan cheese, olive oil, garlic, salt, and pepper. Process until smooth.",
                "Toss the cooked pasta with the pesto until well coated."
            ),
            R.drawable.p4,
            "Italian",
            12
        ),
        Recipe(
            5,
            "Chili Con Carne",
            "A hearty and spicy stew with beef and beans",
            listOf(
                "1 lb ground beef",
                "1 onion, chopped",
                "1 green bell pepper, chopped",
                "2 garlic cloves, minced",
                "2 tablespoons chili powder",
                "1 teaspoon ground cumin",
                "1/2 teaspoon paprika",
                "1/2 teaspoon dried oregano",
                "1/4 teaspoon cayenne pepper",
                "1 can (14 oz) diced tomatoes",
                "1 can (15 oz) kidney beans, drained and rinsed",
                "1 can (8 oz) tomato sauce",
                "Salt and pepper, to taste"
            ),
            listOf(
                "In a large pot or Dutch oven, cook the ground beef over medium-high heat until browned. Drain any excess fat.",
                "Add the onion, bell pepper, and garlic to the pot and cook until softened.",
                "Add the chili powder, cumin, paprika, oregano, and cayenne pepper to the pot and cook for 1 minute.",
                "Add the diced tomatoes (with their juice), kidney beans, and tomato sauce to the pot. Season with salt and pepper.",
                "Bring the chili to a simmer and cook for 30 minutes, stirring occasionally."
            ),
            R.drawable.p5,
            "Mexican",
            13
        ),
        Recipe(
            6,
            "Spinach and Feta Stuffed Chicken",
            "A delicious and easy stuffed chicken recipe",
            listOf(
                "4 boneless, skinless chicken breasts",
                "4 oz fresh spinach",
                "4 oz feta cheese, crumbled",
                "2 garlic cloves, minced",
                "Salt and pepper, to taste",
                "2 tablespoons olive oil"
            ),
            listOf(
                "Preheat the oven to 375°F.",
                "Butterfly the chicken breasts by cutting them horizontally through the center, being careful not to cut all the way through.",
                "In a small bowl, mix together the spinach, feta cheese, garlic, salt, and pepper.",
                "Stuff each chicken breast with the spinach and feta mixture and secure with toothpicks.",
                "Heat the olive oil in an oven-safe skillet over medium-high heat. Add the chicken breasts and cook for 2-3 minutes per side, or until golden brown.",
                "Transfer the skillet to the preheated oven and bake for 20-25 minutes, or until the chicken is cooked through."
            ),
            R.drawable.p6,
            "Mediterranean",
        23),
        Recipe(
            6,
            "Spinach and Feta Stuffed Chicken",
            "A delicious and easy stuffed chicken recipe",
            listOf(
                "4 boneless, skinless chicken breasts",
                "4 oz fresh spinach",
                "4 oz feta cheese, crumbled",
                "2 garlic cloves, minced",
                "Salt and pepper, to taste",
                "2 tablespoons olive oil"
            ),
            listOf(
                "Preheat the oven to 375°F.",
                "Butterfly the chicken breasts by cutting them horizontally through the center, being careful not to cut all the way through.",
                "In a small bowl, mix together the spinach, feta cheese, garlic, salt, and pepper.",
                "Stuff each chicken breast with the spinach and feta mixture and secure with toothpicks.",
                "Heat the olive oil in an oven-safe skillet over medium-high heat. Add the chicken breasts and cook for 2-3 minutes per side, or until golden brown.",
                "Transfer the skillet to the preheated oven and bake for 20-25 minutes, or until the chicken is cooked through."
            ),
            R.drawable.p6,
            "Mediterranean",
            10
        ),
        Recipe(
            6,
            "Spinach and Feta Stuffed Chicken",
            "A delicious and easy stuffed chicken recipe",
            listOf(
                "4 boneless, skinless chicken breasts",
                "4 oz fresh spinach",
                "4 oz feta cheese, crumbled",
                "2 garlic cloves, minced",
                "Salt and pepper, to taste",
                "2 tablespoons olive oil"
            ),
            listOf(
                "Preheat the oven to 375°F.",
                "Butterfly the chicken breasts by cutting them horizontally through the center, being careful not to cut all the way through.",
                "In a small bowl, mix together the spinach, feta cheese, garlic, salt, and pepper.",
                "Stuff each chicken breast with the spinach and feta mixture and secure with toothpicks.",
                "Heat the olive oil in an oven-safe skillet over medium-high heat. Add the chicken breasts and cook for 2-3 minutes per side, or until golden brown.",
                "Transfer the skillet to the preheated oven and bake for 20-25 minutes, or until the chicken is cooked through."
            ),
            R.drawable.p6,
            "Mediterranean",
        22)
    )
}

data class Recipe(
    val id: Int,
    val name: String,
    val description : String,
    val ingredients: List<String>,
    val step: List<String>,
    val image: Int,
    val course : String,
    val time : Int
)
