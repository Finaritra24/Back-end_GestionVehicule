package com.projetfy.gestionvehicule.modelAffichage;

public class Insert {
    public static String insertString(String name){
        String values="<div className=\"flex flex-wrap justify-content-center align-items-center \"gap-2\"><label htmlFor=\""+ name +"\"className=\"w-6rem\">"+name+"</label><InputText value={"+name+"} onChange={(e) => set"+name+"(e.target.value)} placeholder=\""+name+"\"/></div>";
        return values;
    }
    public static String insertDouble(String name){
        String values="<div className=\"flex flex-wrap justify-content-center align-items-center \"gap-2\"><label htmlFor=\""+ name +"\"className=\"w-6rem\">"+name+"</label><InputNumber inputId=\"minmaxfraction\" value={"+name+"} onValueChange={(e) => set"+name+"(e.value)} minFractionDigits={2} maxFractionDigits={2} /></div>";
        return values;
    }
    public static void main(String[]args) {
        System.out.print(insertString("nom"));
    }
}
