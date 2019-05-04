package com.blogger.features.Jsf;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Jsf {

    public static class Msg {

        /**
         * Recupera a mensagem de erro da exception e adciona nas pilha de
         * mensagens do JSF.
         *
         * Exibi no console a pilha de erro
         *
         * @param e - Exception lançada.
         */
        public static void erro(final Exception e) {
            erro(e.getMessage());
            e.printStackTrace();
        }

        /**
         * Adiciona a mensagem na pilha de mensagens do JSF com tipo
         * SEVERITY_INFO
         *
         * @param msg - Mensagem para exibida
         */
        public static void sucesso(final String msg) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, fm);
        }

        /**
         * Adiciona a mensagem na pilha de mensagens do JSF com tipo
         * SEVERITY_WARN
         *
         * @param msg - Mensagem para exibida
         */
        public static void alerta(final String msg) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, fm);
        }

        public static void erro(final String msg) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, fm);
        }

    }

}
