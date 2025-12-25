import { useState } from "react";
import Form from "../../components/Form";
import Field from "../../components/Field";
import Button from "../../components/Button";
import TextArea from "../../components/TextArea";

export default function AddBookForm() {
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [director, setDirector] = useState("");
  const [studio, setStudio] = useState("");

  function handlaSubmit() {
    console.log(`Title: ${title}`);
    console.log(`Genre: ${description}`);
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
        <TextArea
          name="Description"
          placeholder="Enter film description"
          onChange={setDescription}
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
