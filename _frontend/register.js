const API_BASE_URL = "http://localhost:8080/api";
const registerForm = document.getElementById("form-register");

if (registerForm) {
    registerForm.addEventListener("submit", async (event) => {
        event.preventDefault();
        const login = document.getElementById("login").value;
        const password = document.getElementById("password").value;

        const requestBody = {
            login,
            password
        };

        try {
            const response = await fetch(`${API_BASE_URL}/register`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                credentials: "include",
                body: JSON.stringify(requestBody)
            });

            if (response.status === 201) {
                alert("Registration successful!");
                window.location.href = "index.html"; // Перенаправляем на страницу входа
            } else if (response.status === 409) {
                alert("Такой логин уже существует.");
            } else {
                const error = await response.json();
                document.getElementById("register-error").textContent = error.message;
            }
        } catch (error) {
            console.error("Error during registration:", error);
        }
    });
}
