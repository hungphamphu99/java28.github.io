const quizes = [
    {
      id: 1,
      question: "1 + 1 = ?",
      answers: [1, 2, 3, 4],
    },
    {
      id: 2,
      question: "2 + 2 = ?",
      answers: [2, 3, 4, 5],
    },
    {
      id: 3,
      question: "3 + 3 = ?",
      answers: [3, 4, 5, 6],
    },
  ];
  
  document.addEventListener("DOMContentLoaded", function () {
    const quizContainer = document.getElementById("quiz-container");
    const randomBtn = document.getElementById("random-btn");
    // render toàn bộ quiz
    function renderQuiz(data) {
      quizContainer.innerHTML = "";
      data.forEach((quiz) => {
        const questionDiv = document.createElement("div");
        questionDiv.classList.add("question");
        const title = document.createElement("h2");
        title.textContent = `Câu ${quiz.id}: ${quiz.question}`;
        questionDiv.appendChild(title);
        quiz.answers.forEach((ans) => {
          const label = document.createElement("label");
          const input = document.createElement("input");
          input.type = "radio";
          input.name = `question${quiz.id}`;
          input.value = ans;
          label.appendChild(input);
          label.append(` ${ans}`);
          questionDiv.appendChild(label);
          questionDiv.appendChild(document.createElement("br"));
        });
        quizContainer.appendChild(questionDiv);
      });
    }
  
    renderQuiz(quizes);
    // sự kiện bấm nút
    function getRandomIndex(length) {
        return Math.floor(Math.random() * length);
      }
      
      function randomCheckRadio(name) {
        const radios = document.querySelectorAll('input[name="' + name + '"]');
        if (radios.length > 0) {
          const randomIndex = getRandomIndex(radios.length);
          radios[randomIndex].checked = true;
        }
      }
      
      randomBtn.addEventListener("click", function() {
        const questionNames = quizes.map(function(q) {
          return "question" + q.id;
        });
        questionNames.forEach(randomCheckRadio);
      });      
  });
  