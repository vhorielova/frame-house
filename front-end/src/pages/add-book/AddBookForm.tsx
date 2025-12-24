import { useState } from "react";
import Form from "../../components/Form";
import Field from "../../components/Field";
import Button from "../../components/Button";

export default function AddBookForm() {
  const [title, setTitle] = useState("");
  const [genre, setGenre] = useState("");
  const [director, setDirector] = useState("");
  const [studio, setStudio] = useState("");

  function handlaSubmit() {
    console.log(`Title: ${title}`);
    console.log(`Genre: ${genre}`);
    console.log(`Director: ${director}`);

    console.log(`Studio: ${studio}`);
  }

  return (
    <Form title="Add film">
      <div className="fields">
        <Field
          name="Title"
          placeholder="Enter film title"
          onChange={setTitle}
        />
        <Field
          name="Genre"
          placeholder="Enter film genre"
          onChange={setGenre}
        />
        <Field
          name="Director"
          placeholder="Enter the director"
          onChange={setDirector}
        />
        <Field
          name="Studio"
          placeholder="Enter the studio"
          onChange={setStudio}
        />
      </div>

      <Button onClick={handlaSubmit}>ADD</Button>

      {/* <span>title: {title}</span> */}
    </Form>
  );
}
