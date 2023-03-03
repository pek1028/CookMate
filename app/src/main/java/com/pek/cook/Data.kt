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
            4,
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
            5,
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
            "Lemon Garlic Butter Shrimp",
            "A simple and tasty shrimp recipe",
            listOf(
                "1 lb large shrimp, peeled and deveined",
                "4 cloves garlic, minced",
                "1/4 cup butter, melted",
                "1/4 cup fresh lemon juice",
                "Salt and pepper, to taste",
                "1 tablespoon chopped fresh parsley"
            ),
            listOf(
                "Preheat the oven to 375°F.",
                "In a small bowl, whisk together the garlic, butter, lemon juice, salt, and pepper.",
                "Arrange the shrimp in a single layer in a baking dish.",
                "Pour the garlic butter mixture over the shrimp and toss to coat.",
                "Bake for 10-12 minutes, or until the shrimp are pink and cooked through.",
                "Sprinkle with fresh parsley before serving."
            ),
            R.drawable.p7,
            "Seafood",
            20
        ),
        Recipe(
            7,
            name = "Classic Margherita Pizza",
            "A classic Margherita pizza recipe with tomato sauce, mozzarella cheese, and fresh basil.",
            listOf(
                "1 pound pizza dough",
                "1/2 cup tomato sauce",
                "8 ounces fresh mozzarella cheese, sliced",
                "Fresh basil leaves, torn",
                "Salt and pepper, to taste",
                "2 tablespoons olive oil"
            ),
            listOf(
                "Preheat oven to 450°F.",
                "Roll out pizza dough on a floured surface and transfer to a baking sheet or pizza stone.",
                "Spread tomato sauce evenly over the dough, leaving a small border around the edges.",
                "Top with slices of mozzarella cheese and torn basil leaves.",
                "Season with salt and pepper to taste, then drizzle with olive oil.",
                "Bake in preheated oven for 10-15 minutes, or until the crust is golden brown and the cheese is melted and bubbly.",
                "Slice and serve hot."
            ),
            R.drawable.p8,
            "Italian",
            30
        ),
        Recipe(
            8,
            "Beef Stroganoff",
            "A classic beef stroganoff recipe with tender strips of beef, mushrooms, and sour cream sauce.",
            listOf(
                "1 pound beef sirloin, sliced into thin strips",
                "8 ounces egg noodles",
                "8 ounces mushrooms, sliced",
                "1/2 cup beef broth",
                "1/2 cup sour cream",
                "1 onion, chopped",
                "2 garlic cloves, minced",
                "2 tablespoons butter",
                "2 tablespoons flour",
                "Salt and pepper, to taste"
            ),
            listOf(
                "Cook egg noodles according to package instructions.",
                "Melt butter in a large skillet over medium-high heat. Add sliced beef and cook until browned, then remove from skillet.",
                "Add chopped onion and minced garlic to the skillet and cook until softened, then add sliced mushrooms and cook until they release their liquid.",
                "Add flour to the skillet and stir until it's fully incorporated with the vegetables and mushrooms. Gradually add beef broth and stir until the sauce thickens.",
                "Add the cooked beef strips back to the skillet and stir until coated with the sauce. Reduce heat to low and stir in sour cream.",
                "Season with salt and pepper to taste, then serve over cooked egg noodles."
            ),
            R.drawable.p9,
            "Russian",
            45
        ),
        Recipe(
            9,
            "Chicken Fajitas",
            "An easy and flavorful chicken fajitas recipe",
            listOf(
                "1 lb chicken breast, sliced",
                "1 red bell pepper, sliced",
                "1 green bell pepper, sliced",
                "1 onion, sliced",
                "2 tbsp olive oil",
                "1 tbsp chili powder",
                "1 tsp cumin",
                "1/2 tsp garlic powder",
                "1/2 tsp paprika",
                "1/4 tsp salt",
                "Flour tortillas, for serving",
                "Sour cream, for serving",
                "Salsa, for serving",
                "Shredded cheese, for serving"
            ),
            listOf(
                "In a small bowl, mix together the chili powder, cumin, garlic powder, paprika, and salt.",
                "In a large skillet, heat the olive oil over medium-high heat. Add the chicken and cook for 5-7 minutes, or until browned and cooked through.",
                "Add the sliced bell peppers and onion to the skillet with the chicken. Cook for an additional 5-7 minutes, or until the vegetables are tender.",
                "Sprinkle the spice mixture over the chicken and vegetables. Stir well to coat.",
                "Serve the chicken and vegetable mixture in warm flour tortillas with sour cream, salsa, and shredded cheese."
            ),
            R.drawable.p10,
            "Mexican",
            30
        ),
        Recipe(
            10,
            "One-Pot Creamy Garlic Pasta",
            "A simple and delicious pasta dish that's easy to make in just one pot.",
            listOf(
                "12 oz linguine",
                "4 cups vegetable broth",
                "1 cup heavy cream",
                "1/2 cup grated parmesan cheese",
                "4 cloves garlic, minced",
                "2 tablespoons olive oil",
                "Salt and pepper, to taste"
            ),
            listOf(
                "In a large pot or Dutch oven, heat the olive oil over medium-high heat.",
                "Add the garlic and sauté for 1-2 minutes, or until fragrant.",
                "Add the linguine, vegetable broth, and heavy cream to the pot.",
                "Bring to a boil, then reduce the heat and simmer for 10-12 minutes, stirring occasionally, until the pasta is cooked and the sauce has thickened.",
                "Remove from heat and stir in the parmesan cheese. Season with salt and pepper to taste.",
                "Serve hot, garnished with additional parmesan cheese and chopped parsley if desired."
            ),
            R.drawable.p11,
            "Italian",
            25
        ),
        Recipe(
            11,
            "Sheet Pan Salmon and Asparagus",
            "A healthy and flavorful dinner that's easy to prepare using just one sheet pan.",
            listOf(
                "4 salmon fillets",
                "1 lb asparagus, trimmed",
                "1 lemon, thinly sliced",
                "4 cloves garlic, minced",
                "2 tablespoons olive oil",
                "Salt and pepper, to taste"
            ),
            listOf(
                "Preheat the oven to 425°F.",
                "Arrange the salmon fillets and asparagus on a large sheet pan. Scatter the minced garlic over the top.",
                "Drizzle the olive oil over the salmon and asparagus, then season with salt and pepper to taste.",
                "Arrange the lemon slices over the top of the salmon.",
                "Bake for 12-15 minutes, or until the salmon is cooked through and the asparagus is tender.",
                "Serve hot, garnished with additional lemon wedges if desired."
            ),
            R.drawable.p12,
            "Seafood",
            20
        )

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
    val time : Int,
    var isFavorite: Boolean = false,
    var isIngredient: Boolean = false
)
