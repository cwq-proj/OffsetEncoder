/** @type {import('tailwindcss').Config} */
export default {
  content: ["./index.html", "./src/**/*.{js,ts,jsx,tsx}"],
  theme: {
    container: {
      center: true,
      padding: "2rem", // Optional: Add padding if needed
    },
    screens: {
      sm: "480px",
      md: "768px",
      lg: "976px",
      xl: "1440px",
    },
    colors: {
      red: "#dc2626",
      blue: {
        300: "#93c5fd",
        500: "#1fb6ff",
        700: "#0d8eff", // Add new shade of blue
      },
      black: "#000000",
      gray: {
        800: "#1f2937",
        900: "#1f2937",
        950: "#030712",
      },
      white: "#f8fafc",
    },
    fontFamily: {
      sans: ["Graphik", "sans-serif"],
      serif: ["Merriweather", "serif"],
    },
    extend: {
      spacing: {
        128: "32rem",
        144: "36rem",
      },
      borderRadius: {
        "4xl": "2rem",
      },
    },
  },
};
