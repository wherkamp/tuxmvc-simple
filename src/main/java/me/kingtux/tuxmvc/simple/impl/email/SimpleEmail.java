package me.kingtux.tuxmvc.simple.impl.email;

import me.kingtux.tmvc.core.emails.Email;
import me.kingtux.tmvc.core.emails.NameEmail;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.Mailer;

import java.util.List;

public class SimpleEmail implements Email {
    private Mailer mailer;

    @Override
    public String subject() {
        return null;
    }

    @Override
    public String content() {
        return null;
    }

    @Override
    public NameEmail from() {
        return null;
    }

    @Override
    public List<NameEmail> to() {
        return null;
    }

    @Override
    public List<NameEmail> cc() {
        return null;
    }

    @Override
    public void send() {

    }
}
