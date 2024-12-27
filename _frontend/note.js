const API_BASE_URL = "http://localhost:8080/api";
const urlParams = new URLSearchParams(window.location.search);
const noteId = urlParams.get("id");
const noteContainer = document.getElementById("note-container");
const noteForm = document.getElementById("note-form");
const timestampsContainer = document.getElementById("timestamps");

async function fetchNote() {
    try {
        const response = await fetch(`${API_BASE_URL}/note?id=${noteId}`, { method: "GET", credentials: "include" });
        if (response.status === 200) {
            const note = await response.json();
            displayNote(note);
        } else {
            noteContainer.textContent = "Failed to load note.";
        }
    } catch (error) {
        console.error("Error fetching note:", error);
        noteContainer.textContent = "An error occurred.";
    }
}

function displayNote(note) {
    document.getElementById("title").value = note.title;
    document.getElementById("txt").value = note.text;


    const createdAtElement = document.getElementById("created-at");
    const updatedAtElement = document.getElementById("updated-at")
    createdAtElement.textContent = `Создана: ${note.createdAt}`;
    updatedAtElement.textContent = `Обновлена: ${note.updateAt}`;

    noteContainer.style.display = "none";
    noteForm.style.display = "block";
    timestampsContainer.style.display = "block"
}

noteForm.addEventListener("submit", async (event) => {
    event.preventDefault();
    const requestBody = {
        id: noteId,
        title: document.getElementById("title").value,
        text: document.getElementById("txt").value,
        updatedAt: new Date().toISOString(), // Текущее время в формате ISO
    };

    try {
        const response = await fetch(`${API_BASE_URL}/note`, {
            method: "PATCH",
            headers: {
                "Content-Type": "application/json" // Указываем тип контента
            },
            credentials: "include", // Передаем cookie с запросом
            body: JSON.stringify(requestBody),
        });

        if (response.status === 200) {
            alert("Note updated successfully");
            window.location.href = "notes.html";
        } else {
            alert("Failed to update note");
        }
    } catch (error) {
        console.error("Error updating note:", error);
    }
});

document.getElementById("delete-button").addEventListener("click", async () => {
    if (!confirm("Are you sure you want to delete this note?")) return;

    try {
        const response = await fetch(`${API_BASE_URL}/note?id=${noteId}`,
            { method: "DELETE",
                credentials: "include"
            });
        if (response.status === 200) {
            alert("Note deleted successfully");
            window.location.href = "notes.html";
        } else {
            alert("Failed to delete note");
        }
    } catch (error) {
        console.error("Error deleting note:", error);
    }
});

fetchNote();
