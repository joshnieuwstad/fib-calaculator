import axios from "axios";
import { useEffect, useState } from "react";

export default function Fibonacci() {
  const [seenIndices, setIndices] = useState([]);
  const [values, setValues] = useState([]);
  const [currentIndex, setIndex] = useState();

  useEffect(() => {
    fetchIndices();
    fetchValues();
  }, []);

  const fetchValues = async () => {
    const seenValues = await axios.get("api/values/current");
    setValues(seenValues);
  };

  const fetchIndices = async () => {
    const seenIndices = await axios.get("api/values/all");
    setIndices(seenIndices);
  };

  const handleSubmit = async (event) => {
    event.preventDefault();

    await axios.post("/api/values", {
      index: currentIndex,
    });

    setIndices((indices) => [...indices, currentIndex]);
    setIndex(null);
  };

  return (
    <>
      <form onSubmit={handleSubmit}>
        <label>Enter your index:</label>
        <input
          value={currentIndex}
          onChange={(event) => setIndex(event.target.value)}
        />
        <button>Submit</button>
      </form>

      <h3>Indices I have seen:</h3>
      {seenIndices.map((index) => {
        return <div key={index}>{index}</div>;
      })}

      <h3>Values I have calculated</h3>
      {values.map((value) => {
        return (
          <div key={value.index}>
            For {value.index} I calculated {value.value}
          </div>
        );
      })}
    </>
  );
}
