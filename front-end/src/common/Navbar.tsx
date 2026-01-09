import { FaUser } from "react-icons/fa";
import { IoLibrary } from "react-icons/io5";

import "./Navbar.css"
import { Link } from "react-router-dom";


export default function Navbar() {

    return <header className="navbar">

        <span>
        <Link to="/" className="navbar-name icon-link">
            <IoLibrary size={28}/>   
            <span>Frame house</span>
        </Link>
        
        </span>

        <nav>
            <ul>
                <li><Link to="/">Homepage</Link></li>
                <li><Link to="/add-film">Add film</Link></li>
                <li><Link to="/log-out">Log out</Link></li>
                
                <li><Link to="/profile" className="icon-link">
                        <FaUser size={28} />
                    </Link>                
                </li>                
            </ul>
        </nav>
    </header>
    
}