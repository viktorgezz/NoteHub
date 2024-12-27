const API_BASE_URL = "http://localhost:8080/api";
const notesContainer = document.getElementById("notes-container");
const logoutButton = document.getElementById("logout-button");
const newNoteButton = document.getElementById("new-note-button");

async function fetchNotes() {
    try {
        const response = await fetch(`${API_BASE_URL}/notes`, { method: "GET", credentials: "include" });
        if (response.status === 200) {
            const notes = await response.json();
            displayNotes(notes);
        } else if (response.status === 401) {
            window.location.href = "index.html"; // Redirect to login if unauthorized
        } else {
            notesContainer.textContent = "Failed to load notes.";
        }
    } catch (error) {
        console.error("Error fetching notes:", error);
        notesContainer.textContent = "An error occurred.";
    }
}

function displayNotes(notes) {
    if (notes.length === 0) {
        notesContainer.innerHTML = "<p>No notes found.</p>";
        return;
    }

    notesContainer.innerHTML = "";
    const list = document.createElement("ul");

    notes.forEach((note) => {
        const listItem = document.createElement("li");
        listItem.innerHTML = `
            <h3>${note.title}</h3>
            <p>${note.text}</p>
            <a href="note.html?id=${note.id}">View Details</a>
        `;
        list.appendChild(listItem);
    });

    notesContainer.appendChild(list);
}

if (logoutButton) {
    logoutButton.addEventListener("click", async () => {
        try {
            const response = await fetch(`${API_BASE_URL}/logout`, {
                method: "POST",
                credentials: "include",
            });
            if (response.status === 200) {
                window.location.href = "index.html"; // Redirect to login
            } else {
                alert("Logout failed. Please try again.");
            }
        } catch (error) {
            console.error("Logout failed:", error);
            alert("An error occurred during logout.");
        }
    });
}

newNoteButton.addEventListener("click", () => {
    window.location.href = "new-note.html";
});

fetchNotes();
