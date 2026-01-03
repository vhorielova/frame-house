
import Navbar from "../../common/Navbar"
import "./AddBook.css"
import AddBookForm from "./AddBookForm"

export default function AddBook() {


    return <div className="page">
        <Navbar/>

        <div className="content">
            <AddBookForm/>    
        </div>    
        
    </div>
}