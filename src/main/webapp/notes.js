const API_BASE_URL = "http://localhost:8080";
const notesContainer = document.getElementById("notes-container");
const logoutButton = document.getElementById("logout-button");

// Fetch notes
async function fetchNotes() {
    try {
        const response = await fetch(`${API_BASE_URL}/notes`, {
            method: "GET",
            credentials: "include", // Send cookies with request
        });

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

// Display notes
function displayNotes(notes) {
    if (notes.length === 0) {
        notesContainer.innerHTML = "<p>No notes found.</p>";
        return;
    }

    notesContainer.innerHTML = ""; // Clear loading message
    const list = document.createElement("ul");

    notes.forEach((note) => {
        const listItem = document.createElement("li");
        listItem.innerHTML = `
      <h3>${note.title}</h3>
      <p>${note.text}</p>
      <p><strong>Created:</strong> ${note.createdAt}</p>
      <p><strong>Updated:</strong> ${note.updateAt}</p>
    `;
        list.appendChild(listItem);
    });

    notesContainer.appendChild(list);
}

// Logout handler
logoutButton.addEventListener("click", async () => {
    try {
        await fetch(`${API_BASE_URL}/logout`, {
            method: "POST",
            credentials: "include",
        });
        window.location.href = "index.html"; // Redirect to login
    } catch (error) {
        console.error("Logout failed:", error);
    }
});

// Fetch notes on page load
fetchNotes();
