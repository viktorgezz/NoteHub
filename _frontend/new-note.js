const API_BASE_URL = "http://localhost:8080/api";
const newNoteForm = document.getElementById("new-note-form");
const backButton = document.getElementById("back-button");

newNoteForm.addEventListener("submit", async (event) => {
    event.preventDefault(); // Prevent default form submission

    const title = document.getElementById("title").value;
    const text = document.getElementById("text").value;

    try {
        const response = await fetch(`${API_BASE_URL}/note`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            credentials: "include",
            body: JSON.stringify({ title, text }),
        });

        if (response.status === 200) {
            alert("Note saved successfully!");
            window.location.href = "notes.html"; // Redirect to notes page
        } else {
            const errorText = await response.text();
            alert(errorText || "Failed to save note.");
        }
    } catch (error) {
        console.error("Error saving note:", error);
        alert("An error occurred while saving the note.");
    }
});

backButton.addEventListener("click", () => {
    window.location.href = "notes.html"; // Redirect to notes page
});
