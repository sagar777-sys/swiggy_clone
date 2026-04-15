package com.example.swiggyclone.data

object DummyData {

    // 1. Create the menus for different places
    val amarJuiceMenu = listOf(
        MenuItem(1, "Pav Bhaji", 150, "Classic butter pav bhaji", "https://imgs.search.brave.com/A5UdHuoTljGRKv5G1l0c8cR953kXh1oiNZOahWqLx-Q/rs:fit:500:0:1:0/g:ce/aHR0cHM6Ly93d3cu/c2h1dHRlcnN0b2Nr/LmNvbS9pbWFnZS1w/aG90by9wYXYtYmhh/amktc2VydmVkLW9u/LXRyYXktMjYwbnct/MTg3MDQwMDYwMi5q/cGc"),
        MenuItem(2, "Mosambi Juice", 80, "Fresh sweet lime juice", "https://imgs.search.brave.com/W5ijxzTU_OytNEnimXoGKuRQNLP_Z3A-BRZln4wJ_Is/rs:fit:500:0:1:0/g:ce/aHR0cHM6Ly93d3cu/c2h1dHRlcnN0b2Nr/LmNvbS9pbWFnZS1w/aG90by9zd2VldC1s/aW1lLWp1aWNlLW1v/c2FtYmktMjYwbnct/MTQ4MjUwNjA1NC5q/cGc"),
        MenuItem(3, "Masala Dosa", 120, "Crispy dosa with potato filling", "https://imgs.search.brave.com/pZLF8zycT64twlw7gUFnwSK2Q0IYVLBL423Oh8lbZMs/rs:fit:500:0:1:0/g:ce/aHR0cHM6Ly9tZWRp/YS5pc3RvY2twaG90/by5jb20vaWQvMTQz/MDYwNTk2NS9waG90/by9tYXNhbGEtZG9z/YS1pbi1wbGF0ZS1m/b29kLWltYWdlLmpw/Zz9zPTYxMng2MTIm/dz0wJms9MjAmYz1N/blk5TVByY1NhdDds/NVg0M2VRc1dxUHFZ/STV2WDZDTFZqbzEy/Um1YNXRVPQ")
    )

    val burgerKingMenu = listOf(
        MenuItem(4, "Crispy Veg Burger", 70, "Veggie patty with mayo", "https://imgs.search.brave.com/ZgBCM51ngcwitYhmG7NhLyhURyNFHoOECf96dqq49uk/rs:fit:500:0:1:0/g:ce/aHR0cHM6Ly9taW5p/bWFsaXN0YmFrZXIu/Y29tL3dwLWNvbnRl/bnQvdXBsb2Fkcy8y/MDE1LzA3L0FNQVpJ/TkctR1JJTExBQkxF/LVZlZ2dpZS1CdXJn/ZXJzLUhlYXJ0eS1m/bGF2b3JmdWwtYW5k/LWhvbGQtdXAtb24t/dGhlLWdyaWxsLW9y/LXNraWxsZXQtdmVn/YW4tdmVnZ2llYnVy/Z2VyLWdyaWxsaW5n/LWRpbm5lci1oZWFs/dGh5LXJlY2lwZS5q/cGc"),
        MenuItem(5, "Chicken Whopper", 199, "Signature flame-grilled chicken", "https://imgs.search.brave.com/vgRGGwt8cpmvTO3FgHrK61xiBL6IXdtROVh9wp7j7ac/rs:fit:500:0:1:0/g:ce/aHR0cHM6Ly90aHVt/YnMuZHJlYW1zdGlt/ZS5jb20vYi9qdWlj/eS1jaGlja2VuLXpp/bmdlci13aG9wcGVy/LWJ1cmdlci1oYW1i/dXJnZXItY2hlZXNl/YnVyZ2VyLW9uZS1w/YXR0aWVzLXNhdWNl/LWZyZW5jaC1mcmll/cy1jb2xkLWRyaW5r/LWNvbmNlcHQtMjM0/MTUyNTg0LmpwZw"),
        MenuItem(6, "Medium Fries", 99, "Salted golden fries", "https://imgs.search.brave.com/irz39MJb4SrfT_aoB_s84lKCQRSEqyDivOguWQkHFG8/rs:fit:500:0:1:0/g:ce/aHR0cHM6Ly9zdGF0/aWMudmVjdGVlenku/Y29tL3N5c3RlbS9y/ZXNvdXJjZXMvdGh1/bWJuYWlscy8wNTMv/NDQ5LzMwNi9zbWFs/bC9kZWxpY2lvdXMt/Y3Jpc3B5LWZyZW5j/aC1mcmllcy1pbi1h/LW1ldGFsLWJhc2tl/dC1mcmVlLXBuZy5w/bmc")
    )

    val dominosMenu = listOf(
        MenuItem(7, "Margherita Pizza", 250, "Cheese and tomato sauce", "https://imgs.search.brave.com/G32lLpsDoBsGZ2QdcpycJS8u4_6RNahX7hPUh005nHc/rs:fit:500:0:1:0/g:ce/aHR0cHM6Ly9tZWRp/YS5pc3RvY2twaG90/by5jb20vaWQvMTE1/MTk2Njc2Mi9waG90/by9pdGFsaWFuLXBp/enphLW1hcmdoZXJp/dGEuanBnP3M9NjEy/eDYxMiZ3PTAmaz0y/MCZjPWVOXzNSM2t0/WlVnd09lR0prcF95/Q0NlSER2d21sMHFQ/NUp1aHZpeGxtZlE9"),
        MenuItem(8, "Pepperoni Pizza", 350, "Spicy pepperoni and cheese", "https://imgs.search.brave.com/PR1QCfB9LUfv8Dg8lzKQ5ibx_Rf-KU2jgWwlWFEVuwA/rs:fit:500:0:1:0/g:ce/aHR0cHM6Ly9pbWcu/ZnJlZXBpay5jb20v/ZnJlZS1waG90by9w/ZXBwZXJvbmktcGl6/emEtd2l0aC1oZXJi/cy10b3BfMTQxNzkz/LTkwMi5qcGc_c2Vt/dD1haXNfaHlicmlk/Jnc9NzQw")
    )

    // 2. Put them together into a list of Restaurants
    val restaurants = listOf(
        Restaurant(
            id = 1,
            name = "Amar Juice Centre",
            cuisine = "Pav Bhaji, Street Food",
            rating = 4.5,
            deliveryTime = "25 mins",
            imageUrl = "https://picsum.photos/id/1015/300/200"
        ),
        Restaurant(
            id = 2,
            name = "Burger King",
            cuisine = "Burgers, Fast Food",
            rating = 4.1,
            deliveryTime = "30 mins",
            imageUrl = "https://picsum.photos/id/431/300/200"
        ),
        Restaurant(
            id = 3,
            name = "Domino's Pizza",
            cuisine = "Pizza, Italian",
            rating = 4.3,
            deliveryTime = "35 mins",
            imageUrl = "https://picsum.photos/id/1025/300/200"
        )
    )

    // Helper function to get menu items by restaurant ID
    fun getMenuItems(restaurantId: Int): List<MenuItem> {
        return when (restaurantId) {
            1 -> amarJuiceMenu
            2 -> burgerKingMenu
            3 -> dominosMenu
            else -> emptyList()
        }
    }
    data class User(
        val name: String,
        val email: String,
        val phone: String = "+91 9876543210" // Dummy phone number for now
    )
}
