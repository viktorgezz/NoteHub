const API_BASE_URL = "http://localhost:8080"; // URL вашего API

// DOM элементы
const loginForm = document.getElementById("form-login");
const loginError = document.getElementById("login-error");

// Обработчик события отправки формы входа
loginForm.addEventListener("submit", async (event) => {
    event.preventDefault(); // Отменяем стандартное поведение формы

    // Получаем данные из формы
    const login = document.getElementById("login").value;
    const password = document.getElementById("password").value;

    try {
        // Отправляем запрос на сервер
        const response = await fetch(`${API_BASE_URL}/login`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json", // Указываем тип контента
            },
            credentials: "include", // Передаем cookie с запросом
            body: JSON.stringify({ login, password }), // Передаем логин и пароль в теле запроса
        });

        if (response.status === 200) {
            // Если статус успешный, перенаправляем пользователя на страницу заметок
            loginError.textContent = ""; // Очищаем ошибки
            window.location.href = "notes.html"; // Перенаправление
        } else {
            // Если ошибка, отображаем сообщение об ошибке
            const errorText = await response.text();
            loginError.textContent = errorText || "Login failed."; // Указываем текст ошибки
        }
    } catch (error) {
        // Обрабатываем сетевые или другие ошибки
        loginError.textContent = "An error occurred.";
        console.error("Error during login:", error);
    }
});
