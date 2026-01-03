import { FaUser } from "react-icons/fa";
import { IoLibrary } from "react-icons/io5";

import "./Common.css"


export default function Navbar() {

    return <header className="navbar">

        <span>
        <a href="/" className="navbar-name icon-link">
            <IoLibrary size={28}/>   
            <span>Frame house</span>
        </a>
        
        </span>

        <nav>
            <ul>
                <li><a href="/">Homepage</a></li>
                <li><a href="/add-film">Add film</a></li>
                <li><a href="/log-out">Log out</a></li>
                
                <li><a href="/profile" className="icon-link">
                        <FaUser size={28} />
                    </a>                
                </li>                
            </ul>
        </nav>
    </header>
    
}