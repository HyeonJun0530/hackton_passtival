import logo from './logo.svg';
import Home from './pages/Home';
import './App.css';

function App() {
  return (
    <Router>
      <Route path="/" element={<Home />} />
    </Router>
  );
}

export default App;
